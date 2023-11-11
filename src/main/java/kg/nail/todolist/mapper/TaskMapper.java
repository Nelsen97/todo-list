package kg.nail.todolist.mapper;

import kg.nail.todolist.dto.TaskDTO;
import kg.nail.todolist.entity.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskMapper {
    public TaskDTO toTaskDTO(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .created(LocalDateTime.now())
                .completed(false)
                .build();
    }

    public Task toTaskEntity(TaskDTO task) {
        return Task.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .created(LocalDateTime.now())
                .completed(false)
                .build();
    }
}
