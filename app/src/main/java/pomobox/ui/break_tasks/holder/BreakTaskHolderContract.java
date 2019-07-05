package pomobox.ui.break_tasks.holder;


import pomobox.data.model.BreakTask;

public interface BreakTaskHolderContract {
    interface View {
        void viewChecked();
        void viewUnCheck();
        void viewOnDeleteTask();
    }

    interface Presenter {
        void handleDeleteTask();
        void deleteTask(BreakTask task);
        void handleTaskDone(BreakTask task, boolean isCheck);
    }
}
