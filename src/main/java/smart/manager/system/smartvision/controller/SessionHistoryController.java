package smart.manager.system.smartvision.controller;

import com.example.metricsstarter.annotation.CountMetric;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smart.manager.system.smartvision.db.entity.RequestLog;
import smart.manager.system.smartvision.service.history.RequestLogService;
import smart.manager.system.smartvision.model.SessionRequestHistory;

import java.util.List;

@RestController
@RequestMapping("/session")
@AllArgsConstructor
public class SessionHistoryController {
    private RequestLogService requestLogService;
    private SessionRequestHistory sessionRequestHistory;

    @CountMetric
    @GetMapping("/history")
    public ResponseEntity<List<String>> getSessionHistory() {
        List<String> history = sessionRequestHistory.getRequestUrls();
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @GetMapping("/history/{sessionId}")
    public ResponseEntity<List<RequestLog>> getSessionHistory(@PathVariable String sessionId) {
        List<RequestLog> logs = requestLogService.getRequestLogsBySessionId(sessionId);
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @GetMapping("/logs")
    public ResponseEntity<List<RequestLog>> getAllLogs() {
        List<RequestLog> logs = requestLogService.getAllRequestLogs();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addRequest(@RequestBody String requestUrl) {
        requestLogService.saveRequestLog(requestUrl);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/clear")
    public ResponseEntity<Void> clearLogs() {
        requestLogService.clearRequestLogs();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
