package cvds.todo.backend.exceptions;

/**
 * TaskException is a custom exception class that extends AppException.
 * This serves as the base class for all task-related exceptions, allowing
 * specific error handling for different situations like not found, invalid values, or conflicts.
 */
public class TaskException extends AppException {

    /**
     * Constructor for TaskException.
     * @param message The error message.
     * @param statusCode The HTTP status code associated with the error.
     */
    public TaskException(String message, Integer statusCode) {
        super(message, statusCode);
    }

    /**
     * TaskNotFoundException is thrown when a task cannot be found in the database.
     */
    public static class TaskNotFoundException extends TaskException {

        /**
         * Constructor for TaskNotFoundException.
         * @param task The task that was not found.
         */
        public TaskNotFoundException(String task) {
            super("Task: " + task + ", not found in the database.", 404);
        }
    }

    /**
     * TaskInvalidValueException is thrown when an invalid value is encountered.
     */
    public static class TaskInvalidValueException extends TaskException {

        /**
         * Constructor for TaskInvalidValueException.
         * @param value The invalid value encountered.
         */
        public TaskInvalidValueException(String value) {
            super("Invalid value for: " + value, 400);
        }
    }

    /**
     * TaskConflictException is thrown when there is a conflict, such as a duplicate task.
     */
    public static class TaskConflictException extends TaskException {

        /**
         * Constructor for TaskConflictException.
         * @param task The task that caused the conflict.
         */
        public TaskConflictException(String task) {
            super("Task: " + task + ", already exists in the database.", 409);
        }
    }
}
