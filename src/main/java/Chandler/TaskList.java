package Chandler;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) throws ChandlerException {
        if (index < 0 || index >= tasks.size()) {
            throw new ChandlerException("Are you sure this task exists?");
        }
        return tasks.remove(index);
    }

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

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

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
