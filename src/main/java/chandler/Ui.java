package chandler;

import java.util.Scanner;

/**
 * Handles all user interface interactions for the Chandler chatbot.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
        System.setProperty("line.separator", "\n");
    }

    // Displays the welcome message when the Chandler starts.
    public void showWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Chandler\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    //     // Displays the goodbye message when the Chandler exits.
    public void showGoodbye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope I don't see you again!");
        System.out.println("    ____________________________________________________________");
    }

    // Displays error message to the user.
    public void showError(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     OOPS!!! " + message);
        System.out.println("    ____________________________________________________________");
    }

    // Reads command from user.
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a message when a task is successfully added.
     *
     * @param task the task that was added
     * @param totalTasks the new total number of tasks after adding
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays a message when a task is successfully removed.
     *
     * @param task the task that was removed
     * @param totalTasks the new total number of tasks after removal
     */
    public void showTaskRemoved(Task task, int totalTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays a message when a task has been marked or unmarked.
     *
     * @param task the task that was modified
     * @param isDone true if the task was marked as done, false if marked as not done
     */
    public void showTaskMarked(Task task, boolean isDone) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + (isDone ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:"));
        System.out.println("      " + task);
        System.out.println("    ____________________________________________________________");
    }

    // Displays list of all tasks.
    public void showTaskList(TaskList tasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i));
        }
        if (tasks.isEmpty()) {
            System.out.println("    (no tasks yet)");
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays tasks that match the keyword.
     *
     * @param matchingTasks the TaskList containing matching tasks
     * @param keyword the keyword that was searched for
     */
    public void showMatchingTasks(TaskList matchingTasks, String keyword) {
        System.out.println("    ____________________________________________________________");
        if (matchingTasks.isEmpty()) {
            System.out.println("    No tasks found containing: '" + keyword + "'");
        } else {
            System.out.println("    Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    public void close() {
        scanner.close();
    }
}