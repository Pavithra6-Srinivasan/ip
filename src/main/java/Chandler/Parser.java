package Chandler;

// Parses user input into commands that the chatbot can understand.
public class Parser {

    // Parses user input string into a Command object.
    public static Command parse(String input) throws ChandlerException {

        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case "bye":
            return new Command(CommandType.BYE);
        case "list":
            return new Command(CommandType.LIST);
        case "mark":
            return new Command(CommandType.MARK, arguments);
        case "unmark":
            return new Command(CommandType.UNMARK, arguments);
        case "todo":
            return new Command(CommandType.TODO, arguments);
        case "deadline":
            return new Command(CommandType.DEADLINE, arguments);
        case "event":
            return new Command(CommandType.EVENT, arguments);
        case "delete":
            return new Command(CommandType.DELETE, arguments);
        default:
            throw new ChandlerException("I have no idea what that means");
        }
    }
}