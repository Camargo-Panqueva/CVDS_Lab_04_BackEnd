package cvds.todo.backend.controller;

import cvds.todo.backend.model.TaskModel;
import cvds.todo.backend.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class Controller {

    private final TaskService taskService;

    public Controller(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskModel>> getAllTasks() {
        List<TaskModel> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskModel> getTaskById(@PathVariable("id") int id) {
        TaskModel task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping("/new")
    public ResponseEntity<UUID> createTask(@RequestBody TaskModel task) {

        TaskModel newTask = new TaskModel(UUID.randomUUID(), task);

        return ResponseEntity.ok(newTask.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable("id") int id, @RequestBody TaskModel task) {
        TaskModel updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") int id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
