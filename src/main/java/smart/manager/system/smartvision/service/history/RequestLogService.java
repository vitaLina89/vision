package smart.manager.system.smartvision.service.history;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import smart.manager.system.smartvision.db.RequestLogRepository;
import smart.manager.system.smartvision.db.entity.RequestLog;
import smart.manager.system.smartvision.model.SessionRequestHistory;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestLogService {

    private RequestLogRepository requestLogRepository;
    private SessionRequestHistory sessionRequestHistory;
    private final HttpSession httpSession; // Для получения идентификатора сессии



    public void saveRequestLog(String requestUrl) {
        String sessionId = httpSession.getId(); // Получаем идентификатор сессии

        RequestLog log = new RequestLog();
        log.setRequestUrl(requestUrl);
        log.setTimestamp(LocalDateTime.now());
        log.setSessionId(sessionId); // Сохраняем идентификатор сессии в логах

        requestLogRepository.save(log);

        sessionRequestHistory.addRequestUrl(requestUrl);
    }

    public List<RequestLog> getRequestLogsBySessionId(String sessionId) {
        return requestLogRepository.findBySessionId(sessionId);
    }

    public List<RequestLog> getAllRequestLogs() {
        return requestLogRepository.findAll();
    }

    public void clearRequestLogs() {
        requestLogRepository.deleteAll();
        sessionRequestHistory.clearHistory(); // Очищаем историю запросов для текущей сессии
    }
}
