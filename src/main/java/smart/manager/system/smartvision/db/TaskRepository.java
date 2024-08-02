package smart.manager.system.smartvision.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import smart.manager.system.smartvision.db.entity.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByDate(LocalDate date);
}
