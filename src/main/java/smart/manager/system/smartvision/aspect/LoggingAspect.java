package smart.manager.system.smartvision.aspect;



import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;


@Aspect
public class LoggingAspect {
    private static final Logger LOGGER  = LoggerFactory.getLogger(LoggingAspect.class);


    // within указывает на вызовы конкретного класса или пакет
    @Pointcut("within(smart.manager.system.smartvision.service.history.*)")
    public void anyRequestLogServiceMethod(){
    }

    @Before("anyRequestLogServiceMethod()")
    public void beforeAnyRequestLogServiceMethod(){
        LOGGER.info("===== BEFORE ====");
    }

    // Перед любым публичным методом с любым количеством параметров
    @Before("execution(* *(..))")
    public void beforeAnyMethod(){
        LOGGER.info("===== BEFORE ====");
    }
    @Pointcut("@annotation(postMapping)")
    public void anyPostMappingMethod(PostMapping postMapping) {
    }

    @After(value = "anyPostMappingMethod(postMapping)", argNames = "postMapping")
    public void afterAnyPostMappingMethod(PostMapping postMapping) {
        LOGGER.info("Добавлена новая задача");
    }
}
