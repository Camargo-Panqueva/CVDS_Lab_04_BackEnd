package cvds.todo.backend;

import cvds.todo.backend.controller.TaskController;
import cvds.todo.backend.model.TaskModel;
import cvds.todo.backend.services.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskService taskService;

    private TaskModel task;

    @BeforeEach
    void setUp() {
        task = new TaskModel();
        task.setId(UUID.randomUUID().toString());
        task.setName("Test Task");
        task.setDescription("This is a test task.");
    }

    @Test
    void getAllTasks_ShouldReturnListOfTasks() throws Exception {
        when(taskService.getAllTasks()).thenReturn(Collections.singletonList(task));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(task.getId()))
                .andExpect(jsonPath("$[0].name").value(task.getName()))
                .andExpect(jsonPath("$[0].description").value(task.getDescription()));

        verify(taskService).getAllTasks();
    }

    @Test
    void createTask_ShouldCreateAndReturnTask() throws Exception {
        when(taskService.createTask(any(TaskModel.class))).thenReturn(task);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(task.getId()))
                .andExpect(jsonPath("$.name").value(task.getName()))
                .andExpect(jsonPath("$.description").value(task.getDescription()));

        verify(taskService).createTask(any(TaskModel.class));
    }

    @Test
    void getTaskById_ShouldReturnTask() throws Exception {
        when(taskService.getTaskById(task.getId())).thenReturn(task);

        mockMvc.perform(get("/tasks/{id}", task.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(task.getId()))
                .andExpect(jsonPath("$.name").value(task.getName()))
                .andExpect(jsonPath("$.description").value(task.getDescription()));

        verify(taskService).getTaskById(task.getId());
    }

    @Test
    void deleteTask_ShouldDeleteAndReturnTask() throws Exception {
        when(taskService.deleteTask(task.getId())).thenReturn(task);

        mockMvc.perform(delete("/tasks/{id}", task.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(task.getId()));

        verify(taskService).deleteTask(task.getId());
    }

    @Test
    void updateTask_ShouldUpdateAndReturnTask() throws Exception {
        TaskModel updatedTask = new TaskModel();
        updatedTask.setId(task.getId());
        updatedTask.setName("Updated Task");
        updatedTask.setDescription("Updated description.");

        when(taskService.updateTask(eq(task.getId()), any(TaskModel.class))).thenReturn(updatedTask);

        mockMvc.perform(patch("/tasks/{id}", task.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTask)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedTask.getId()))
                .andExpect(jsonPath("$.name").value(updatedTask.getName()))
                .andExpect(jsonPath("$.description").value(updatedTask.getDescription()));

        verify(taskService).updateTask(eq(task.getId()), any(TaskModel.class));
    }
}