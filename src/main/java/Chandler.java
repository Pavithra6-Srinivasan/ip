import java.util.Scanner;

public class Chandler {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Chandler\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        boolean isRunning = true;

        while (isRunning) {
            String input = in.nextLine();

            switch (input.toLowerCase()) {
            case "bye":
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope I don't see you again!");
                System.out.println("    ____________________________________________________________");
                isRunning = false;
                break;

            case "list":
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks[i]);
                }
                if (taskCount == 0) {
                    System.out.println("    (no tasks yet)");
                }
                System.out.println("    ____________________________________________________________");
                break;

            default:
                tasks[taskCount] = input;
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
