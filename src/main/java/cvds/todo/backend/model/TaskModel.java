package cvds.todo.backend.model;

public class TaskModel {
    private final int id;
    private String name;
    private String description;
    private boolean done;

    public TaskModel(int id, String name, String description, boolean done, boolean important) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.done = done;
    }

    public TaskModel(int id) {
        this.id = id;
    }

    public int getId() {
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
