package smart.manager.system.smartvision.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import smart.manager.system.smartvision.db.entity.Task;
import smart.manager.system.smartvision.model.RequestTraceId;
import smart.manager.system.smartvision.model.SessionRequestHistory;

@Component
@Scope("prototype")
@RequiredArgsConstructor
@Slf4j
public class TaskProcessor {

    private Task task;

    private final RequestTraceId requestTraceId;


    public void setTask(Task task) {
        this.task = task;
    }


    public void process() {
        // Логика обработки задачи
        log.info("Processing req:{},task:{}",requestTraceId, task.getTitle());
        validateTask();
    }

    private void validateTask() {
        // Пример валидации
        if (task.getDate() == null) {
            throw new IllegalArgumentException("Task date cannot be null");
        }
        if (task.getTime() == null) {
            throw new IllegalArgumentException("Task time cannot be null");
        }
    }
}
