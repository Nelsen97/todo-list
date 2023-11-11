package kg.nail.todolist.controller;


import kg.nail.todolist.dto.TaskDTO;
import kg.nail.todolist.exception.CustomException;
import kg.nail.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody TaskDTO task) {
        try {
            taskService.createTask(task);
            return new ResponseEntity<>("Задача успешно создана!", HttpStatus.CREATED);
        } catch (CustomException e) {
            log.error("", e);
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        try {
            return new ResponseEntity<>(taskService.updateTask(id, taskDTO), HttpStatus.OK);
        } catch (CustomException e) {
            log.error("", e);
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
        } catch (CustomException e) {
            log.error("", e);
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long id) {
        try {
            taskService.deleteTaskById(id);
            return new ResponseEntity<>("Задача удалена", HttpStatus.OK);
        } catch (CustomException e) {
            log.error("", e);
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

}
