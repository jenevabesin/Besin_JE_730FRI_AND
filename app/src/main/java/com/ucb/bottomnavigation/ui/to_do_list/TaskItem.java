package com.ucb.bottomnavigation.ui.to_do_list;

public class TaskItem {
    private String taskName;
    private boolean isChecked;
    private int imageResId;

    public TaskItem(String taskName, boolean isChecked, int imageResId) {
        this.taskName = taskName;
        this.isChecked = isChecked;
        this.imageResId = imageResId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName)
    {
        this.taskName =taskName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked)
    {
        isChecked = checked;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId)
    {
        this.imageResId = imageResId;
    }
}
