package smart.manager.system.smartvision.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import smart.manager.system.smartvision.service.TaskProcessor;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
@Slf4j
public class SessionRequestHistory {

    private List<String> requestUrls = new ArrayList<>();

    public List<String> getRequestUrls() {
        return requestUrls;
    }

    public void addRequestUrl(String requestUrl) {
        requestUrls.add(requestUrl);
    }

    public void clearHistory() {
        requestUrls.clear();
    }
}
