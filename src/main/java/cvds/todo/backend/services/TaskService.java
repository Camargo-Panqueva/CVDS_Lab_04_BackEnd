package cvds.todo.backend.services;

import cvds.todo.backend.interfeces.TasksService;
import cvds.todo.backend.model.TaskModel;
import cvds.todo.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Servicio que implementa la lógica para gestionar las tareas.
 * Se comunica con el repositorio de tareas para realizar operaciones CRUD.
 */
@Service
public class TaskService implements TasksService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Obtiene todas las tareas desde el repositorio.
     *
     * @return Lista de tareas almacenadas en el repositorio.
     */
    @Override
    public ArrayList<TaskModel> getAllTasks() {
        // Lógica para obtener todas las tareas
        return new ArrayList<>();
    }

    /**
     * Obtiene una tarea por su ID desde el repositorio.
     *
     * @param id Identificador de la tarea.
     * @return La tarea correspondiente al ID o null si no se encuentra.
     */
    @Override
    public TaskModel getTaskById(int id) {
        // Lógica para obtener una tarea por su ID
        return null;
    }

    /**
     * Crea una nueva tarea y la almacena en el repositorio.
     *
     * @param task Modelo de la nueva tarea a crear.
     * @return La tarea creada.
     */
    @Override
    public TaskModel createTask(TaskModel task) {
        // Lógica para crear una nueva tarea
        return null;
    }

    /**
     * Actualiza una tarea existente en el repositorio.
     *
     * @param id   Identificador de la tarea a actualizar.
     * @param task Modelo de la tarea con los nuevos valores.
     * @return La tarea actualizada.
     */
    @Override
    public TaskModel updateTask(int id, TaskModel task) {
        // Lógica para actualizar una tarea existente
        return null;
    }

    /**
     * Elimina una tarea del repositorio por su ID.
     *
     * @param id Identificador de la tarea a eliminar.
     */
    @Override
    public void deleteTask(int id) {
        // Lógica para eliminar una tarea
    }
}
