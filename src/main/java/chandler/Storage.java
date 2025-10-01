package chandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Handles loading and saving of tasks.
public class Storage {
    private String filePath;
    private String directoryPath;

    // Constructs a new Storage instance with the specified file path.
    public Storage(String filePath) {
        this.filePath = filePath;
        this.directoryPath = new File(filePath).getParent();
    }

    /**
     * Loads tasks from the storage file. If the file does not exist, it creates the directory structure
     * and returns an empty task list.
     *
     * @return an ArrayList of tasks loaded from the file
     * @throws ChandlerException if there is an error reading the file
     */
    public ArrayList<Task> load() throws ChandlerException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                new File(directoryPath).mkdirs();
                return tasks;
            }

            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;

                Task task = parseTaskFromLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new ChandlerException("Error loading data: " + e.getMessage());
        }
    }

    /**
     * Save current list of tasks to storage file.
     *
     * @param tasks the TaskList containing tasks to save
     * @throws ChandlerException if there is an error writing to the file
     */
    public void save(TaskList tasks) throws ChandlerException {
        try {
            new File(directoryPath).mkdirs();
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new ChandlerException("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the storage file into a Task object.
     *
     * @param line line to parse
     * @return the parsed Task object, or null if parsing fails
     */
    private Task parseTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) return null;

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length >= 4) {
                task = new Deadline(description, parts[3]);
            }
            break;
        case "E":
            if (parts.length >= 5) {
                task = new Event(description, parts[3], parts[4]);
            }
            break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }
}