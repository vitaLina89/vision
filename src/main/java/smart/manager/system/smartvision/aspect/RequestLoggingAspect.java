package smart.manager.system.smartvision.aspect;

import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import smart.manager.system.smartvision.service.history.RequestLogService;

@Aspect
@Component
@AllArgsConstructor
public class RequestLoggingAspect {

    private final RequestLogService requestLogService;

    @AfterReturning("execution(* smart.manager.system.smartvision.controller..*(..))")
    public void logRequest(JoinPoint joinPoint) {
        String requestUrl = joinPoint.getSignature().toShortString();
        requestLogService.saveRequestLog(requestUrl);
    }
}
