package cvds.todo.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class TaskModel {
    @JsonIgnore
    private UUID id;
    private String name;
    private String description;
    private boolean done;

    public TaskModel() {}

    public TaskModel(UUID id, TaskModel task) {
        this.id = id;
        this.name = task.getName();
        this.description = task.getDescription();
        this.done = task.isDone();
    }

    public TaskModel(UUID id, String name, String description, boolean done) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.done = done;
    }

    public TaskModel(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
