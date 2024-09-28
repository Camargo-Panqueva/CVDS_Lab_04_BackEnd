package cvds.todo.backend;

import cvds.todo.backend.repository.TaskRepository;
import cvds.todo.backend.services.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración que gestiona la creación de beans para el servicio de tareas
 * y su repositorio.
 */
@Configuration
public class Config {

    /**
     * Crea y configura el bean de TaskService, que gestiona la lógica de las tareas.
     *
     * @return Una nueva instancia de TaskService.
     */
    @Bean
    public TaskService taskService() {
        return new TaskService();
    }

    /**
     * Crea y configura el bean de TaskRepository, que maneja las operaciones de persistencia
     * de las tareas.
     *
     * @return Una nueva instancia de TaskRepository.
     */
    @Bean
    public TaskRepository taskRepository() {
        return new TaskRepository();
    }

}