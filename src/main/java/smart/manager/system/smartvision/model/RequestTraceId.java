package smart.manager.system.smartvision.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestTraceId {

    private String traceId;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public void processRequest() {
        // Логика обработки данных, специфичных для текущего запроса
        System.out.println("Processing request with Trace ID: " + traceId);
    }
}
