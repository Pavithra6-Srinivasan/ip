import java.util.Scanner;

public class Chandler {
    public static void main(String[] args) {

        System.setProperty("line.separator", "\n");

        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Chandler\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        boolean isRunning = true;

        while (isRunning) {
            String input = in.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();

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
                int markIndex = Integer.parseInt(parts[1]) - 1;
                tasks[markIndex].markAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + tasks[markIndex]);
                System.out.println("    ____________________________________________________________");
                break;

            case "unmark":
                int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                tasks[unmarkIndex].markAsNotDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("      " + tasks[unmarkIndex]);
                System.out.println("    ____________________________________________________________");
                break;

            case "todo":
                tasks[taskCount] = new Todo(parts[1]);
                System.out.println("    ____________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tasks[taskCount]);
                System.out.println("    Now you have " + (taskCount + 1) + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
                taskCount++;
                break;

            case "deadline":
                String[] deadlineParts = parts[1].split(" /by ", 2);
                if (deadlineParts.length < 2) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Please use the format: deadline <description> /by <time>");
                    System.out.println("    ____________________________________________________________");
                    break;
                }
                tasks[taskCount] = new Deadline(deadlineParts[0], deadlineParts[1]);
                System.out.println("    ____________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tasks[taskCount]);
                System.out.println("    Now you have " + (taskCount + 1) + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
                taskCount++;
                break;

            case "event":
                String[] eventParts = parts[1].split(" /from ", 2);
                if (eventParts.length < 2) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Please use the format: event <description> /from <start> /to <end>");
                    System.out.println("    ____________________________________________________________");
                    break;
                }
                String[] timeParts = eventParts[1].split(" /to ", 2);
                if (timeParts.length < 2) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Please use the format: event <description> /from <start> /to <end>");
                    System.out.println("    ____________________________________________________________");
                    break;
                }
                tasks[taskCount] = new Event(eventParts[0], timeParts[0], timeParts[1]);
                System.out.println("    ____________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tasks[taskCount]);
                System.out.println("    Now you have " + (taskCount + 1) + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
                taskCount++;
                break;

            default:
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("    ____________________________________________________________");
                System.out.println("    added: " + input);
                System.out.println("    ____________________________________________________________");
                break;
            }
        }
        in.close();
    }
}