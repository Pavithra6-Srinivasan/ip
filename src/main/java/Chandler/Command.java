package Chandler;

public class Command {

    private CommandType type;
    private String arguments;

    public Command(CommandType type) {
        this(type, "");
    }

    public Command(CommandType type, String arguments) {
        this.type = type;
        this.arguments = arguments;
    }

    public CommandType getType() {
        return type;
    }

    public String getArguments() {
        return arguments;
    }
}