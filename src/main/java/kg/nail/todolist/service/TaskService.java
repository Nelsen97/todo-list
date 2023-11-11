package kg.nail.todolist.service;

import kg.nail.todolist.dto.TaskDTO;
import kg.nail.todolist.entity.Task;

import java.util.List;

public interface TaskService {
    void createTask(TaskDTO taskDTO);
    TaskDTO updateTask(Long taskId, TaskDTO task);
    TaskDTO getTaskById(Long taskId);
    List<Task> getAllTasks();
    void deleteTaskById(Long id);
}
