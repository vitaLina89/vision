package smart.manager.system.smartvision.db.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private String dayOfWeek;
    private String time; // формат времени "HH:mm"
    private LocalDate date;
}