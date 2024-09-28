package cvds.todo.backend.controller;

import cvds.todo.backend.service.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private final TaskService taskService;

    public Controller(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task/all")
    public String getTask() {
        return "from /my-todo";
    }

    @GetMapping("/task/{id}")
    public String getTaskById() {
        return "from get /tasks/{id}";
    }

    @PostMapping("/task/new")
    public String newTask() {
        return "from post /new-task";
    }

    @PutMapping("/task/{id}")
    public String updateTask() {
        return "from put /tasks/{id}";
    }

    @DeleteMapping("/task/{id}")
    public String deleteTask() {
        return "from delete /tasks/{id}";
    }
}