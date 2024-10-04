package cvds.todo.backend.interfeces;

import cvds.todo.backend.model.TaskModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

public interface TaskRepository extends MongoRepository<TaskModel, String> {

    @Query("{ '_id' : ?0 }")
    @Update("{ $set: { 'name': ?1, 'description': ?2, 'isDone': ?3 } }")
    void updateById(String id, String name, String description, boolean isDone);
}
