package chandler;

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

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // Adds a task to list.
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index the index of the task to remove
     * @return the removed task
     * @throws ChandlerException if the index is invalid
     */
    public Task remove(int index) throws ChandlerException {
        if (index < 0 || index >= tasks.size()) {
            throw new ChandlerException("Are you sure this task exists?");
        }
        return tasks.remove(index);
    }

    /**
     * Returns the task of the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     */
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

    /**
     * Marks or unmarks a task at the specified index.
     *
     * @param index the index of the task to mark/unmark
     * @param isDone true to mark as done, false to mark as not done
     * @throws ChandlerException if the index is invalid
     */
    public void markTask(int index, boolean isDone) throws ChandlerException {
        Task task = getUserTask(index);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword the keyword to search for
     * @return a list containing only the matching tasks
     */
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
