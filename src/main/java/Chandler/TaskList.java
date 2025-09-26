package Chandler;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides operations to manage them.
 * This includes storage, manipulation, add, remove, mark, and find tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // Adds a task to list.
    public void add(Task task) {
        tasks.add(task);
    }

    // Removes a task from the list.
    public Task remove(int index) throws ChandlerException {
        if (index < 0 || index >= tasks.size()) {
            throw new ChandlerException("Are you sure this task exists?");
        }
        return tasks.remove(index);
    }

    // Returns the task at the specified index.
    public Task get(int index) {
        return tasks.get(index);
    }

    public Task getUserTask(int index) throws ChandlerException {
        if (index < 0 || index >= tasks.size()) {
            throw new ChandlerException("Are you sure this task exists?");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    // Returns whether the task list is empty.
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    // Marks or unmarks a task at the specified index.
    public void markTask(int index, boolean isDone) throws ChandlerException {
        Task task = getUserTask(index);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }

    public TaskList find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }
}
