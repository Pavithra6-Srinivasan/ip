package Chandler;

import java.util.Scanner;
import java.util.ArrayList;

public class Chandler {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String DATA_FILE_PATH = "./data/chandler.txt";

    public static void main(String[] args) {

        System.setProperty("line.separator", "\n");
        Scanner in = new Scanner(System.in);

        printWelcomeMessage();

        boolean isRunning = true;

        while (isRunning) {
            String input = in.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();

            try {
                switch (command) {
                case "bye":
                    handleExit();
                    isRunning = false;
                    break;

                case "list":
                    handleList();
                    break;

                case "mark":
                    handleMark(parts);
                    break;

                case "unmark":
                    handleUnmark(parts);
                    break;

                case "todo":
                    handleTodo(parts);
                    break;

                case "deadline":
                    handleDeadline(parts);
                    break;

                case "event":
                    handleEvent(parts);
                    break;

                case "delete":
                    handleDelete(parts);
                    break;

                default:
                    throw new ChandlerException("I have no idea what that means");
                }
            } catch (ChandlerException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     OOPS!!! " + e.getMessage());
                System.out.println("    ____________________________________________________________");
            }
        }
        in.close();
    }

    private static void printWelcomeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Chandler\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    private static void handleList() {
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

    private static void handleExit() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope I don't see you again!");
        System.out.println("    ____________________________________________________________");
    }

    private static void handleMark(String[] parts) throws ChandlerException {
        if (parts.length < 2) {
            throw new ChandlerException("I guess I'll mark nothing then");
        }

        int markIndex = Integer.parseInt(parts[1]) - 1;
        tasks.get(markIndex).markAsDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + tasks.get(markIndex));
        System.out.println("    ____________________________________________________________");
    }

    private static void handleUnmark(String[] parts) throws ChandlerException {
        if (parts.length < 2) {
            throw new ChandlerException("I guess I'll unmark nothing then");
        }

        int unmarkIndex = Integer.parseInt(parts[1]) - 1;
        tasks.get(unmarkIndex).markAsNotDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + tasks.get(unmarkIndex));
        System.out.println("    ____________________________________________________________");
    }

    private static void handleTodo(String[] parts) throws ChandlerException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChandlerException("I guess you are doing nothing then");
        }

        tasks.add(new Todo(parts[1]));
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + tasks.get(tasks.size() - 1));
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    private static void handleDeadline(String[] parts) throws ChandlerException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChandlerException("Are you never going to finish this?");
        }

        String[] deadlineParts = parts[1].split(" /by ", 2);

        tasks.add(new Deadline(deadlineParts[0], deadlineParts[1]));
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + tasks.get(tasks.size() - 1));
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    private static void handleEvent(String[] parts) throws ChandlerException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChandlerException("Are you sure this event exits?");
        }

        String[] eventParts = parts[1].split(" /from ", 2);
        String[] timeParts = eventParts[1].split(" /to ", 2);

        tasks.add(new Event(eventParts[0], timeParts[0], timeParts[1]));
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + tasks.get(tasks.size() - 1));
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    private static void handleDelete(String[] parts) throws ChandlerException {
        if (parts.length < 2) {
            throw new ChandlerException("Please specify which task to delete.");
        }

        try {
            int deleteIndex = Integer.parseInt(parts[1]) - 1;
            if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
                throw new ChandlerException("Invalid task number. Please choose a task between 1 and " + tasks.size());
            }

            Task removedTask = tasks.remove(deleteIndex);
            System.out.println("    ____________________________________________________________");
            System.out.println("    Noted. I've removed this task:");
            System.out.println("      " + removedTask);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new ChandlerException("Please provide a valid task number.");
        }
    }
}
