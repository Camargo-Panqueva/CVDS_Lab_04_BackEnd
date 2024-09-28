package cvds.todo.backend.controller;

import cvds.todo.backend.model.TaskModel;
import cvds.todo.backend.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controlador REST para la gestión de tareas (TaskModel).
 * Proporciona endpoints para crear, leer, actualizar y eliminar tareas.
 */
@RestController
@RequestMapping("/task")
public class Controller {

    private final TaskService taskService;

    /**
     * Constructor que inyecta el servicio de tareas.
     *
     * @param taskService Servicio para gestionar las tareas.
     */
    public Controller(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Obtener todas las tareas.
     *
     * @return Lista de todas las tareas.
     */
    @GetMapping("/all")
    public ResponseEntity<List<TaskModel>> getAllTasks() {
        List<TaskModel> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Obtener una tarea por su ID.
     *
     * @param id Identificador de la tarea.
     * @return La tarea correspondiente al ID proporcionado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskModel> getTaskById(@PathVariable("id") int id) {
        TaskModel task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * Crear una nueva tarea.
     *
     * @param task Modelo de tarea enviado en el cuerpo de la solicitud.
     * @return El UUID de la nueva tarea creada.
     */
    @PostMapping("/new")
    public ResponseEntity<UUID> createTask(@RequestBody TaskModel task) {
        TaskModel newTask = new TaskModel(UUID.randomUUID(), task);
        return ResponseEntity.ok(newTask.getId());
    }

    /**
     * Actualizar una tarea existente por su ID.
     *
     * @param id   Identificador de la tarea a actualizar.
     * @param task Modelo de tarea actualizado.
     * @return La tarea actualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable("id") int id, @RequestBody TaskModel task) {
        TaskModel updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Eliminar una tarea por su ID.
     *
     * @param id Identificador de la tarea a eliminar.
     * @return Respuesta sin contenido.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") int id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}

