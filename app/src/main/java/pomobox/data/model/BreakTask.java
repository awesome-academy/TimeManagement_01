package pomobox.data.model;

import java.io.Serializable;

public class BreakTask implements Serializable {
    private int mTaskId;
    private int mIsDone;
    private String mTaskTitle;
    private String mTaskContent;
    private int mTargetPomo;
    private int mProgressPomo;

    public BreakTask() {
    }

    public BreakTask(int isDone, String taskTitle, String taskContent, int progressPomo, int targetPomo) {
        mIsDone = isDone;
        mTaskTitle = taskTitle;
        mTaskContent = taskContent;
        mProgressPomo = progressPomo;
        mTargetPomo = targetPomo;
    }

    public int getTaskId() {
        return mTaskId;
    }

    public void setTaskId(int taskId) {
        mTaskId = taskId;
    }

    public int getIsDone() {
        return mIsDone;
    }

    public void setIsDone(int isDone) {
        mIsDone = isDone;
    }

    public String getTaskTitle() {
        return mTaskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        mTaskTitle = taskTitle;
    }

    public String getTaskContent() {
        return mTaskContent;
    }

    public void setTaskContent(String taskContent) {
        mTaskContent = taskContent;
    }

    public int getTargetPomo() {
        return mTargetPomo;
    }

    public void setTargetPomo(int targetPomo) {
        mTargetPomo = targetPomo;
    }

    public int getProgressPomo() {
        return mProgressPomo;
    }

    public void setProgressPomo(int progressPomo) {
        mProgressPomo = progressPomo;
    }
}
