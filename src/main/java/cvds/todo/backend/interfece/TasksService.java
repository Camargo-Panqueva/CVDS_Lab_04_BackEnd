package cvds.todo.backend.interfece;

import cvds.todo.backend.model.TaskModel;

import java.util.ArrayList;

public interface TasksService {

    public ArrayList<TaskModel> getAllTasks();

    public TaskModel getTaskById(int id);

    public TaskModel createTask();

    public TaskModel updateTask(int id);

    public void deleteTask(int id);
}
