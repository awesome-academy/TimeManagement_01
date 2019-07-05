package pomobox.ui.break_tasks.holder;

import pomobox.data.database.BreakTaskHelperDB;
import pomobox.data.model.BreakTask;

public class BreakTaskHolderPresenter implements BreakTaskHolderContract.Presenter {

    private BreakTaskHolderContract.View mView;
    private BreakTaskHelperDB mHelper;

    BreakTaskHolderPresenter(BreakTaskAdapter.ViewHolder viewHolder, BreakTaskHelperDB helper) {
        mView = viewHolder;
        mHelper = helper;
    }


    @Override
    public void handleDeleteTask() {
        if(mHelper.getTaskCount() != 0){
            mView.viewOnDeleteTask();
        }
    }

    @Override
    public void deleteTask(BreakTask task) {
        mHelper.deleteTaskData(task.getTaskId());
    }

    @Override
    public void handleTaskDone(BreakTask task, boolean isCheck) {
        int done = isCheck?1:0;
        task.setIsDone(done);
        if(isCheck) {
            mView.viewChecked();
        }
        else {
            mView.viewUnCheck();
        }
        mHelper.updateTaskData(task,String.valueOf(task.getTaskId()));
    }
}

