package cvds.todo.backend.service;

import cvds.todo.backend.exceptions.AppException;
import cvds.todo.backend.exceptions.TaskException;
import cvds.todo.backend.interfeces.TaskRepository;
import cvds.todo.backend.model.TaskModel;
import cvds.todo.backend.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskServiceTest {

    // Define reusable constants for cleaner and more maintainable code
    private static final String EXISTING_TASK_ID = UUID.randomUUID().toString();
    private static final String NON_EXISTING_TASK_ID = "1";
    private static final String TASK_NAME = "Task 1";
    private static final String TASK_DESCRIPTION = "Description 1";
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTasks_ShouldReturnAllTasks() throws AppException {
        // Arrange
        List<TaskModel> expectedTasks = Arrays.asList(
                this.genTaskModel("1", "Task 1", "Description 1", false),
                this.genTaskModel("2", "Task 2", "Description 2", true)
        );
        when(taskRepository.findAll()).thenReturn(expectedTasks);

        List<TaskModel> actualTasks = taskService.getAllTasks();

        assertEquals(expectedTasks, actualTasks, "Expected tasks do not match actual tasks.");
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void getTaskById_ExistingTask_ShouldReturnTask() throws AppException {
        TaskModel expectedTask = this.genTaskModel(EXISTING_TASK_ID, TASK_NAME, TASK_DESCRIPTION, false);
        when(taskRepository.findById(EXISTING_TASK_ID)).thenReturn(Optional.of(expectedTask));

        TaskModel actualTask = taskService.getTaskById(EXISTING_TASK_ID);

        assertEquals(expectedTask, actualTask, "The returned task should match the expected task.");
        verify(taskRepository, times(1)).findById(EXISTING_TASK_ID);
    }

    @Test
    void getTaskById_NonExistingTask_ShouldThrowTaskNotFoundException() {
        when(taskRepository.findById(NON_EXISTING_TASK_ID)).thenReturn(Optional.empty());

        TaskException.TaskNotFoundException thrownException = assertThrows(
                TaskException.TaskNotFoundException.class,
                () -> taskService.getTaskById(NON_EXISTING_TASK_ID)
        );

        assertEquals("Task: " + NON_EXISTING_TASK_ID + ", not found in the database.", thrownException.getMessage());
        verify(taskRepository, times(1)).findById(NON_EXISTING_TASK_ID);
    }

    @Test
    void createTask_ShouldReturnCreatedTask() throws AppException {
        TaskModel taskToCreate = this.genTaskModel(null, TASK_NAME, TASK_DESCRIPTION, false);
        TaskModel expectedTask = this.genTaskModel(UUID.randomUUID().toString(), TASK_NAME, TASK_DESCRIPTION, false);
        when(taskRepository.insert(any(TaskModel.class))).thenReturn(expectedTask);

        TaskModel createdTask = taskService.createTask(taskToCreate);

        assertNotNull(createdTask.getId(), "Created task should have a generated ID.");
        assertEquals(expectedTask.getName(), createdTask.getName(), "Task name should match.");
        assertEquals(expectedTask.getDescription(), createdTask.getDescription(), "Task description should match.");
        assertEquals(expectedTask.isDone(), createdTask.isDone(), "Task status should match.");
        verify(taskRepository, times(1)).insert(any(TaskModel.class));
    }

    @Test
    void updateTask_ExistingTask_ShouldReturnUpdatedTask() throws AppException {
        TaskModel existingTask = this.genTaskModel(EXISTING_TASK_ID, "Old Task", "Old Description", false);
        TaskModel updatedTask = this.genTaskModel(EXISTING_TASK_ID, "Updated Task", "Updated Description", true);
        when(taskRepository.findById(EXISTING_TASK_ID)).thenReturn(Optional.of(existingTask));
        when(taskRepository.existsById(EXISTING_TASK_ID)).thenReturn(true);
        when(taskRepository.insert(any(TaskModel.class))).thenReturn(updatedTask);

        TaskModel result = taskService.updateTask(EXISTING_TASK_ID, updatedTask);

        assertEquals(updatedTask, result, "Updated task should match the provided task model.");
        verify(taskRepository, times(1)).findById(EXISTING_TASK_ID);
        verify(taskRepository, times(1)).save(any(TaskModel.class));
    }

    @Test
    void updateTask_NonExistingTask_ShouldThrowTaskNotFoundException() {
        TaskModel updatedTask = this.genTaskModel(NON_EXISTING_TASK_ID, "Updated Task", "Updated Description", true);
        when(taskRepository.findById(NON_EXISTING_TASK_ID)).thenReturn(Optional.empty());

        TaskException.TaskNotFoundException thrownException = assertThrows(
                TaskException.TaskNotFoundException.class,
                () -> taskService.updateTask(NON_EXISTING_TASK_ID, updatedTask)
        );

        assertEquals("Task: " + NON_EXISTING_TASK_ID + ", not found in the database.", thrownException.getMessage());
        verify(taskRepository, times(1)).findById(NON_EXISTING_TASK_ID);
    }

    @Test
    void deleteTask_ExistingTask_ShouldDeleteTask() throws AppException {
        TaskModel taskToDelete = this.genTaskModel(EXISTING_TASK_ID, TASK_NAME, TASK_DESCRIPTION, false);
        when(taskRepository.existsById(EXISTING_TASK_ID)).thenReturn(true);
        when(taskRepository.findById(EXISTING_TASK_ID)).thenReturn(Optional.of(taskToDelete));

        TaskModel deletedTask = taskService.deleteTask(EXISTING_TASK_ID);

        assertEquals(taskToDelete, deletedTask, "Deleted task should match the existing task.");
        verify(taskRepository, times(1)).existsById(EXISTING_TASK_ID);
        verify(taskRepository, times(1)).findById(EXISTING_TASK_ID);
        verify(taskRepository, times(1)).deleteById(EXISTING_TASK_ID);
    }

    @Test
    void deleteTask_NonExistingTask_ShouldThrowTaskNotFoundException() {
        when(taskRepository.existsById(NON_EXISTING_TASK_ID)).thenReturn(false);

        TaskException.TaskNotFoundException thrownException = assertThrows(
                TaskException.TaskNotFoundException.class,
                () -> taskService.deleteTask(NON_EXISTING_TASK_ID)
        );

        assertEquals("Task: " + NON_EXISTING_TASK_ID + ", not found in the database.", thrownException.getMessage());
        verify(taskRepository, times(1)).existsById(NON_EXISTING_TASK_ID);
    }

    private TaskModel genTaskModel(String taskId, String taskName, String taskDescription, boolean done) {
        final TaskModel newTask = new TaskModel();
        newTask.setId(taskId);
        newTask.setName(taskName);
        newTask.setDescription(taskDescription);
        newTask.setDone(done);
        return newTask;
    }
}
