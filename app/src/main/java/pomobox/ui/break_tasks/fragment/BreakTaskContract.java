package pomobox.ui.break_tasks.fragment;

import android.app.Dialog;

import pomobox.data.model.BreakTask;

public interface BreakTaskContract {
    interface View{
        void showAddNewTask();
        void showInputEmpty();
        void showAddTaskSuccess(BreakTask breakTask, Dialog dialog);
    }
    interface Presenter{
        void handleShowAddBreakTask();
        void checkAddBreakTask(Dialog dialog, String title, String content, int target);
    }
}
