package smart.manager.system.smartvision.controller;


import com.example.metricsstarter.annotation.CountMetric;
import com.example.metricsstarter.annotation.SuccessErrorMetric;
import com.example.metricsstarter.annotation.TimeMetric;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smart.manager.system.smartvision.db.TaskRepository;
import smart.manager.system.smartvision.db.entity.Task;
import smart.manager.system.smartvision.model.RequestTraceId;
import smart.manager.system.smartvision.service.TaskProcessor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {


    private TaskRepository taskRepository;

    private ApplicationContext context;

    private RequestTraceId requestTraceId;

    @TimeMetric("time")
    @SuccessErrorMetric("status")
    @PostMapping(value = "/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        // Генерация уникального идентификатора запроса
        String traceId = UUID.randomUUID().toString();
        requestTraceId.setTraceId(traceId);
        requestTraceId.processRequest();
        // Создание нового обработчика запроса
        Task savedTask = taskRepository.save(task);
        TaskProcessor taskProcessor = context.getBean(TaskProcessor.class);
        taskProcessor.setTask(savedTask);
        taskProcessor.process();
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }
    @TimeMetric
    @CountMetric
    @SuccessErrorMetric
    @PutMapping(value = "/update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        if (taskRepository.existsById(task.getId())) {
            // Генерация уникального идентификатора запроса
            String traceId = UUID.randomUUID().toString();
            requestTraceId.setTraceId(traceId);
            requestTraceId.processRequest();
            // Создание нового обработчика запроса
            Task updatedTask = taskRepository.save(task);
            TaskProcessor taskProcessor = context.getBean(TaskProcessor.class);
            taskProcessor.setTask(updatedTask);
            taskProcessor.process();
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CountMetric
    @TimeMetric
    @SuccessErrorMetric
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id) {
        // Генерация уникального идентификатора запроса
        String traceId = UUID.randomUUID().toString();
        requestTraceId.setTraceId(traceId);
        requestTraceId.processRequest();
        Optional<Task> task = taskRepository.findById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        // Генерация уникального идентификатора запроса
        String traceId = UUID.randomUUID().toString();
        requestTraceId.setTraceId(traceId);
        requestTraceId.processRequest();
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        // Генерация уникального идентификатора запроса
        String traceId = UUID.randomUUID().toString();
        requestTraceId.setTraceId(traceId);
        requestTraceId.processRequest();
        List<Task> tasks = taskRepository.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/get/by/date/{date}")
    public ResponseEntity<List<Task>> getTasksByDate(@PathVariable String date) {
        // Генерация уникального идентификатора запроса
        String traceId = UUID.randomUUID().toString();
        requestTraceId.setTraceId(traceId);
        requestTraceId.processRequest();
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Task> tasks = taskRepository.findByDate(localDate);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
