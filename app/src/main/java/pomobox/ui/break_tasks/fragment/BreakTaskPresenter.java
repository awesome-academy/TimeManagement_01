package pomobox.ui.break_tasks.fragment;

import android.app.Dialog;

import pomobox.data.database.BreakTaskHelperDB;
import pomobox.data.model.BreakTask;

import static pomobox.utils.Constants.DEFAULT_ZERO_VALUE;

public class BreakTaskPresenter implements BreakTaskContract.Presenter {

    private BreakTaskContract.View mView;
    private BreakTaskHelperDB mHelperDB;

    BreakTaskPresenter(BreakTaskContract.View view, BreakTaskHelperDB helperDB) {
        mView = view;
        mHelperDB = helperDB;
    }

    @Override
    public void handleShowAddBreakTask() {
        mView.showAddNewTask();
    }

    @Override
    public void checkAddBreakTask(Dialog dialog, String title, String content, int target) {
        if(title.length() == 0){
            mView.showInputEmpty();
        }
        else {
            BreakTask taskData = new BreakTask(DEFAULT_ZERO_VALUE, title, content, DEFAULT_ZERO_VALUE, target);
            mHelperDB.insertTaskData(taskData);
            mView.showAddTaskSuccess(taskData, dialog);
        }
    }
}
