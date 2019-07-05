package pomobox.ui.break_tasks.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pomobox.R;
import pomobox.base.BaseCustomDialog;
import pomobox.base.BaseFragment;
import pomobox.data.model.BreakTask;
import pomobox.data.database.BreakTaskHelperDB;
import pomobox.ui.break_tasks.holder.BreakTaskAdapter;

public class BreakTasksFragment extends BaseFragment implements BreakTaskContract.View {

    private BreakTaskHelperDB mHelper;
    private RecyclerView mRecyclerBreakTask;
    private ArrayList<BreakTask> mTaskDataList;
    private BreakTaskAdapter mAdapter;
    private Context mContext;
    private ImageButton mButtonAdd;
    private BreakTaskPresenter mTaskPresenter;
    public static BreakTasksFragment newInstance() {
        return new BreakTasksFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_break_tasks;
    }

    @Override
    public void onViewReady(View view) {
        mContext = getContext();
        mRecyclerBreakTask = view.findViewById(R.id.recycler_break_tasks);
        mButtonAdd = view.findViewById(R.id.button_add_list);
        mHelper = new BreakTaskHelperDB(mContext);
        mTaskDataList = mHelper.getAllTasks();
        mTaskPresenter = new BreakTaskPresenter(this, mHelper);
        doRecyclerView();
        setButtonAdd();
    }

    private void doRecyclerView(){
        mRecyclerBreakTask.setHasFixedSize(false);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerBreakTask.setLayoutManager(mLinearLayoutManager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        mRecyclerBreakTask.addItemDecoration(mDividerItemDecoration);
        mRecyclerBreakTask.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new BreakTaskAdapter(mContext, mTaskDataList, mHelper);
        mRecyclerBreakTask.setAdapter(mAdapter);
        //Set default animation for recycler view
    }

    private void setButtonAdd(){
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Action move to add break task dialog
                mTaskPresenter.handleShowAddBreakTask();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHelper.closeDB();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAddNewTask() {
        BaseCustomDialog addDialog = new BaseCustomDialog(mContext, R.layout.dialog_add_break_task) {
            @Override
            protected void onBindView(final Dialog dialog) {
                final TextView textTargetTask = dialog.findViewById(R.id.text_show_target_task);
                final EditText edtTitle = dialog.findViewById(R.id.text_enter_title_task);
                final EditText edtContent = dialog.findViewById(R.id.text_enter_content_task);
                Button btnAdd = dialog.findViewById(R.id.button_create_task);
                final SeekBar seekBar = dialog.findViewById(R.id.seekbar_target_pomodoro);
                seekBar.setMax(10);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    seekBar.setMin(1);
                }
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        textTargetTask.setText(String.valueOf(progress));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String taskTitle = edtTitle.getText().toString();
                        String taskContent = edtContent.getText().toString();
                        int targetPomodoro = seekBar.getProgress();
                        mTaskPresenter.checkAddBreakTask(dialog, taskTitle, taskContent, targetPomodoro);
                    }
                });
            }
        };
        addDialog.showDialog();
    }

    @Override
    public void showInputEmpty() {
        Toast.makeText(mContext, getString(R.string.warning_title_empty), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAddTaskSuccess(BreakTask breakTask, Dialog dialog) {
        if(breakTask != null) mTaskDataList.add(breakTask);
        Toast.makeText(mContext,getString(R.string.toast_save), Toast.LENGTH_SHORT).show();
        mAdapter.notifyDataSetChanged();
        dialog.dismiss();
    }
}
