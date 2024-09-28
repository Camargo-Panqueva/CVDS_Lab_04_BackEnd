package cvds.todo.backend;

import cvds.todo.backend.repository.TaskRepository;
import cvds.todo.backend.service.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

    @Bean
    public TaskService taskService() {
        return new TaskService();
    }

    @Bean
    public TaskRepository taskRepository() {
        return new TaskRepository();
    }

}
