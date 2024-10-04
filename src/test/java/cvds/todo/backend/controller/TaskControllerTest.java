package cvds.todo.backend.controller;

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

/**
 * Integration test for {@link TaskController}.
 * This class contains unit tests for controller methods
 * related to task management, ensuring expected behaviors
 * when interacting with the task service.
 */
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskService taskService;

    private TaskModel task;

    /**
     * Sets up a TaskModel object before each test.
     * A unique ID is generated, and the name and description fields are initialized.
     */
    @BeforeEach
    void setUp() {
        task = new TaskModel();
        task.setId(UUID.randomUUID().toString());
        task.setName("Test Task");
        task.setDescription("This is a test task.");
    }

    /**
     * Tests retrieval of a list of tasks.
     * Mocks the task service response and verifies
     * that the HTTP response is correct and contains expected data.
     *
     * @throws Exception if an error occurs during test execution.
     */
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

    /**
     * Tests creation of a new task.
     * Mocks the task service response and verifies
     * that the HTTP response is correct and contains the created task data.
     *
     * @throws Exception if an error occurs during test execution.
     */
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

    /**
     * Tests retrieval of a task by its ID.
     * Mocks the task service response and verifies
     * that the HTTP response is correct and contains the requested task data.
     *
     * @throws Exception if an error occurs during test execution.
     */
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

    /**
     * Tests deletion of a task.
     * Mocks the task service response and verifies
     * that the HTTP response is correct and contains the ID of the deleted task.
     *
     * @throws Exception if an error occurs during test execution.
     */
    @Test
    void deleteTask_ShouldDeleteAndReturnTask() throws Exception {
        when(taskService.deleteTask(task.getId())).thenReturn(task);

        mockMvc.perform(delete("/tasks/{id}", task.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(task.getId()));

        verify(taskService).deleteTask(task.getId());
    }

    /**
     * Tests updating a task.
     * Mocks the task service response and verifies
     * that the HTTP response is correct and contains the updated task data.
     *
     * @throws Exception if an error occurs during test execution.
     */
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