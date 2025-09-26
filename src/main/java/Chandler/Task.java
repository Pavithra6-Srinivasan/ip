package Chandler;

/**
 * This class provides common functionality for all task types including
 * description storage, completion status, and basic operations.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    // Constructs a new Task
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Returns the status icon representing whether the task is done.
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    // Returns a string representation of the task.
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    // Returns the description of the task.
    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    // Returns a string representation of the task in file format for storage.
    public abstract String toFileFormat();
}
