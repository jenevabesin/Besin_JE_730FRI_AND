package ModelClass;

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

    public boolean isChecked() {
        return isChecked;
    }

    public int getImageResId() {
        return imageResId;
    }

}
