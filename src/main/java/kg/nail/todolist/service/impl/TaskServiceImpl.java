package kg.nail.todolist.service.impl;

import kg.nail.todolist.dto.TaskDTO;
import kg.nail.todolist.entity.Task;
import kg.nail.todolist.exception.CustomException;
import kg.nail.todolist.mapper.TaskMapper;
import kg.nail.todolist.repository.TaskRepository;
import kg.nail.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public void createTask(TaskDTO taskDTO) {
        if (taskDTO.getDescription() == null || taskDTO.getTitle() == null) {
            throw new CustomException("Все необходимые поля должны быть заполнены!", HttpStatus.BAD_REQUEST);
        }
        Task task = taskMapper.toTaskEntity(taskDTO);
        taskRepository.save(task);
    }

    @Override
    public TaskDTO updateTask(Long taskId, TaskDTO taskDTO) {
        Task task = taskRepository.findTaskById(taskId).orElseThrow(
                () -> new CustomException("Нет такой задачи!", HttpStatus.NOT_FOUND)
        );
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        return taskMapper.toTaskDTO(taskRepository.save(task));
    }

    @Override
    public TaskDTO getTaskById(Long taskId) {
        Task task = taskRepository.findTaskById(taskId).orElseThrow(
                () -> new CustomException("Нет такой задачи!", HttpStatus.NOT_FOUND)
        );
        return taskMapper.toTaskDTO(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTaskById(Long id) {
        Task task = taskRepository.findTaskById(id).orElseThrow(
                () -> new CustomException("Нет такой задачи!", HttpStatus.NOT_FOUND)
        );
        taskRepository.delete(task);
    }
}
