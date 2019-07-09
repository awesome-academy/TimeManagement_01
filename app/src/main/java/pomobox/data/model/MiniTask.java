package pomobox.data.model;

import java.io.Serializable;

import static pomobox.utils.Constants.DEFAULT_ZERO_VALUE;

public class MiniTask implements Serializable {
    private int mTaskId;
    private int mIsDone;
    private String mTaskTitle;
    private String mTaskContent;
    private int mTargetPomo;
    private int mProgressPomo;

    public MiniTask() {
    }

    public MiniTask(String taskTitle, String taskContent, int targetPomo) {
        mIsDone = DEFAULT_ZERO_VALUE;
        mTaskTitle = taskTitle;
        mTaskContent = taskContent;
        mProgressPomo = DEFAULT_ZERO_VALUE;
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
