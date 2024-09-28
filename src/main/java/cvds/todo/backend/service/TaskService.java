package cvds.todo.backend.service;

import cvds.todo.backend.interfece.TasksRepository;
import cvds.todo.backend.interfece.TasksService;
import cvds.todo.backend.model.TaskModel;
import cvds.todo.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TaskService implements TasksService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public ArrayList<TaskModel> getAllTasks() {
        return null;
    }

    @Override
    public TaskModel getTaskById(int id) {
        return null;
    }

    @Override
    public TaskModel createTask(TaskModel task) {
        return null;
    }

    @Override
    public TaskModel updateTask(int id, TaskModel task) {
        return null;
    }

    @Override
    public void deleteTask(int id) {

    }
}
