package smart.manager.system.smartvision.db;


import org.springframework.data.mongodb.repository.MongoRepository;
import smart.manager.system.smartvision.db.entity.RequestLog;

import java.util.List;

public interface RequestLogRepository extends MongoRepository<RequestLog, String> {
    List<RequestLog> findBySessionId(String sessionId);
}
