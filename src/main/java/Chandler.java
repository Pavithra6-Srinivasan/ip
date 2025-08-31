import java.util.Scanner;

public class Chandler {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

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

            default:
                System.out.println("    ____________________________________________________________");
                System.out.println("    " + input);
                System.out.println("    ____________________________________________________________");
                break;
            }
        }
        in.close();
    }
}
