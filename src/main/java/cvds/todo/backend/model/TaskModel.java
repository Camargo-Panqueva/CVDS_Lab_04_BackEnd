package cvds.todo.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class TaskModel {
    @JsonIgnore
    private UUID id;
    private String name;
    private String description;
    @JsonIgnore
    private boolean done;

    public TaskModel() {
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
