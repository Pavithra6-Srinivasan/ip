import java.util.Scanner;

public class Chandler {
    public static void main(String[] args) {

        System.setProperty("line.separator", "\n");

        Scanner in = new Scanner(System.in);
        final int LIST_CAPACITY = 100;
        Task[] tasks = new Task[LIST_CAPACITY];
        int taskCount = 0;

        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Chandler\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        boolean isRunning = true;

        while (isRunning) {
            String input = in.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();

            try {
                switch (command) {
                case "bye":
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Bye. Hope I don't see you again!");
                    System.out.println("    ____________________________________________________________");
                    isRunning = false;
                    break;

                case "list":
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("    " + (i + 1) + "." + tasks[i]);
                    }
                    if (taskCount == 0) {
                        System.out.println("    (no tasks yet)");
                    }
                    System.out.println("    ____________________________________________________________");
                    break;

                case "mark":
                    if (parts.length < 2) {
                        throw new ChandlerException("I guess I'll mark nothing then");
                    }

                    int markIndex = Integer.parseInt(parts[1]) - 1;
                    tasks[markIndex].markAsDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + tasks[markIndex]);
                    System.out.println("    ____________________________________________________________");
                    break;

                case "unmark":
                    if (parts.length < 2) {
                        throw new ChandlerException("I guess I'll unmark nothing then");
                    }

                    int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                    tasks[unmarkIndex].markAsNotDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("      " + tasks[unmarkIndex]);
                    System.out.println("    ____________________________________________________________");
                    break;

                case "todo":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new ChandlerException("I guess you are doing nothing then");
                    }
                    if (taskCount >= LIST_CAPACITY) {
                        throw new ChandlerException("Task list is full! Cannot add more tasks.");
                    }

                    tasks[taskCount] = new Todo(parts[1]);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + tasks[taskCount]);
                    System.out.println("    Now you have " + (taskCount + 1) + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                    taskCount++;
                    break;

                case "deadline":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new ChandlerException("Are you never going to finish this?");
                    }
                    if (taskCount >= LIST_CAPACITY) {
                        throw new ChandlerException("Task list is full! Cannot add more tasks.");
                    }

                    String[] deadlineParts = parts[1].split(" /by ", 2);

                    tasks[taskCount] = new Deadline(deadlineParts[0], deadlineParts[1]);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + tasks[taskCount]);
                    System.out.println("    Now you have " + (taskCount + 1) + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                    taskCount++;
                    break;

                case "event":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new ChandlerException("Are you sure this event exits?");
                    }
                    if (taskCount >= LIST_CAPACITY) {
                        throw new ChandlerException("Task list is full! Cannot add more tasks.");
                    }

                    String[] eventParts = parts[1].split(" /from ", 2);
                    String[] timeParts = eventParts[1].split(" /to ", 2);

                    tasks[taskCount] = new Event(eventParts[0], timeParts[0], timeParts[1]);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + tasks[taskCount]);
                    System.out.println("    Now you have " + (taskCount + 1) + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                    taskCount++;
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
}
