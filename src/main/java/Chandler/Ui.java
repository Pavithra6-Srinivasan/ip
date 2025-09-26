package Chandler;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
        System.setProperty("line.separator", "\n");
    }

    public void showWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Chandler\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope I don't see you again!");
        System.out.println("    ____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     OOPS!!! " + message);
        System.out.println("    ____________________________________________________________");
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void showTaskRemoved(Task task, int totalTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void showTaskMarked(Task task, boolean isDone) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + (isDone ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:"));
        System.out.println("      " + task);
        System.out.println("    ____________________________________________________________");
    }

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

    public void close() {
        scanner.close();
    }
}