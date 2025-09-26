package Chandler;

/**
 * The main class for the Chandler chatbot.
 * Chandler helps users manage their tasks including todos, deadlines, and events.
 * The application supports adding, listing, marking, unmarking, deleting, and finding tasks.
 * Data is automatically saved to and loaded from a file.
 */
public class Chandler {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // Constructs a new Chandler instance with the specified file path for data storage.
    public Chandler(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChandlerException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot and begins processing user commands.
     * The method runs in a loop until the user enters the "bye" command.
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                isRunning = executeCommand(command);
            } catch (ChandlerException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    // Executes the given command and returns whether the chatbot should continue running.
    private boolean executeCommand(Command command) throws ChandlerException {
        switch (command.getType()) {
        case BYE:
            handleExit();
            return false;

        case LIST:
            handleList();
            break;

        case MARK:
            handleMark(command.getArguments());
            storage.save(tasks);
            break;

        case UNMARK:
            handleUnmark(command.getArguments());
            storage.save(tasks);
            break;

        case TODO:
            handleTodo(command.getArguments());
            storage.save(tasks);
            break;

        case DEADLINE:
            handleDeadline(command.getArguments());
            storage.save(tasks);
            break;

        case EVENT:
            handleEvent(command.getArguments());
            storage.save(tasks);
            break;

        case DELETE:
            handleDelete(command.getArguments());
            storage.save(tasks);
            break;
        }
        return true;
    }

    // Lists all tasks.
    private void handleList() {
        ui.showTaskList(tasks);
    }

    // Exits Chandler.
    private void handleExit() {
        ui.showGoodbye();
    }

    // Marks a specific task.
    private void handleMark(String arguments) throws ChandlerException {
        if (arguments.isEmpty()) {
            throw new ChandlerException("I guess I'll mark nothing then");
        }
        int index = parseIndex(arguments);
        tasks.markTask(index, true);
        ui.showTaskMarked(tasks.getUserTask(index), true);
    }

    // Unmarks a specific task.
    private void handleUnmark(String arguments) throws ChandlerException {
        if (arguments.isEmpty()) {
            throw new ChandlerException("I guess I'll unmark nothing then");
        }
        int index = parseIndex(arguments);
        tasks.markTask(index, false);
        ui.showTaskMarked(tasks.getUserTask(index), false);
    }

    // Adds a task to list.
    private void handleTodo(String arguments) throws ChandlerException {
        if (arguments.isEmpty()) {
            throw new ChandlerException("I guess you are doing nothing then");
        }
        Task task = new Todo(arguments);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
    }

    // Adds a task with deadline to list.
    private void handleDeadline(String arguments) throws ChandlerException {
        if (arguments.isEmpty()) {
            throw new ChandlerException("Are you never going to finish this?");
        }
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2) {
            throw new ChandlerException("Please use the format: deadline <description> /by <time>");
        }
        Task task = new Deadline(parts[0], parts[1]);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
    }

    // Adds a task with dates to list.
    private void handleEvent(String arguments) throws ChandlerException {
        if (arguments.isEmpty()) {
            throw new ChandlerException("Are you sure this event exists?");
        }
        String[] eventParts = arguments.split(" /from ", 2);

        if (eventParts.length < 2) {
            throw new ChandlerException("Please use the format: event <description> /from <start> /to <end>");
        }
        String[] timeParts = eventParts[1].split(" /to ", 2);

        if (timeParts.length < 2) {
            throw new ChandlerException("Please use the format: event <description> /from <start> /to <end>");
        }

        Task task = new Event(eventParts[0], timeParts[0], timeParts[1]);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
    }

    // Deletes a task from list.
    private void handleDelete(String arguments) throws ChandlerException {
        if (arguments.isEmpty()) {
            throw new ChandlerException("Please specify which task to delete.");
        }
        int index = parseIndex(arguments);
        Task removedTask = tasks.remove(index);
        ui.showTaskRemoved(removedTask, tasks.size());
    }

    private int parseIndex(String indexString) throws ChandlerException {
        try {
            return Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new ChandlerException("Please provide a valid task number.");
        }
    }

    public static void main(String[] args) {
        new Chandler("./data/chandler.txt").run();
    }
}
