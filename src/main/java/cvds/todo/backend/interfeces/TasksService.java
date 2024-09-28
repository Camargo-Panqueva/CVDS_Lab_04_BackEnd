package cvds.todo.backend.interfeces;

import cvds.todo.backend.exceptions.AppException;
import cvds.todo.backend.model.TaskModel;

import java.util.ArrayList;
import java.util.UUID;

public interface TasksService {

    public ArrayList<TaskModel> getAllTasks() throws AppException;

    public TaskModel getTaskById(UUID id) throws AppException;

    public TaskModel createTask(TaskModel task) throws AppException;

    public TaskModel updateTask(UUID id, TaskModel task) throws AppException;

    public TaskModel deleteTask(UUID id) throws AppException;
}
