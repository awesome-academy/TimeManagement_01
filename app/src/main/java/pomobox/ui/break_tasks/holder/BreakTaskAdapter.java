package pomobox.ui.break_tasks.holder;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pomobox.R;
import pomobox.base.BaseAleartDialog;
import pomobox.data.model.BreakTask;
import pomobox.data.database.BreakTaskHelperDB;

import static pomobox.utils.Constants.ONE_VAUE;

public class BreakTaskAdapter extends RecyclerView.Adapter<BreakTaskAdapter.ViewHolder>{
    private ArrayList<BreakTask> mListTask;
    private Context mContext;
    private BreakTaskHelperDB mHelper;

    public BreakTaskAdapter(Context context, ArrayList<BreakTask> listTask, BreakTaskHelperDB helper) {
        mListTask = listTask;
        mContext = context;
        mHelper = helper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        View itemView = mLayoutInflater.inflate(R.layout.item_break_task, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        mHelper = new BreakTaskHelperDB(mContext);
        boolean check = mListTask.get(i).getIsDone() == ONE_VAUE;
        viewHolder.mCBTaskDone.setChecked(check);
        viewHolder.mTextTaskTitle.setText(mListTask.get(i).getTaskTitle());
        viewHolder.mTextTaskContent.setText(mListTask.get(i).getTaskContent());
        String progress = mListTask.get(i).getProgressPomo()+
                mContext.getString(R.string.separate_progress)+mListTask.get(i).getTargetPomo();
        viewHolder.mTextTaskProgress.setText(progress);
        if(viewHolder.mCBTaskDone.isChecked()) viewHolder.mTextTaskTitle.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        else viewHolder.mTextTaskTitle.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return mListTask.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, BreakTaskHolderContract.View{
        private CheckBox mCBTaskDone;
        private TextView mTextTaskTitle, mTextTaskContent, mTextTaskProgress;
        private ImageButton mButtonDel;
        private BreakTaskHolderPresenter mTaskHolderPresenter;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCBTaskDone = itemView.findViewById(R.id.checkbox_done);
            mTextTaskTitle = itemView.findViewById(R.id.text_title);
            mTextTaskContent = itemView.findViewById(R.id.text_content);
            mTextTaskProgress = itemView.findViewById(R.id.text_progress);
            mButtonDel = itemView.findViewById(R.id.button_del_task);
            mTaskHolderPresenter = new BreakTaskHolderPresenter(this, mHelper);
            mButtonDel.setOnClickListener(this);
            mCBTaskDone.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            BreakTask task = mListTask.get(getAdapterPosition());
            mTaskHolderPresenter.handleTaskDone(task, isChecked);
        }

        @Override
        public void onClick(View v) {
            mTaskHolderPresenter.handleDeleteTask();
        }

        @Override
        public void viewChecked() {
            mTextTaskTitle.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            mTextTaskTitle.setTypeface(mTextTaskTitle.getTypeface(),Typeface.BOLD_ITALIC);
        }

        @Override
        public void viewUnCheck() {
            mTextTaskTitle.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
            mTextTaskTitle.setTypeface(mTextTaskTitle.getTypeface(),Typeface.BOLD);
        }

        @Override
        public void viewOnDeleteTask() {
            BaseAleartDialog aleartDialog = new BaseAleartDialog(mContext,
                    mContext.getString(R.string.app_name),
                    mContext.getString(R.string.ask_for_delete),
                    mContext.getString(R.string.aleart_no),
                    mContext.getString(R.string.aleart_yes),
                    R.mipmap.ic_icon) {
                @Override
                protected void actionClickPositive() {
                    mTaskHolderPresenter.deleteTask(mListTask.get(getAdapterPosition()));
                    mListTask.remove(getAdapterPosition());
                    Toast.makeText(mContext, mContext.getString(R.string.toast_delete), Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }

                @Override
                public void actionClickNegative() {

                }
            };
            aleartDialog.showDialog();
        }
    }
}
