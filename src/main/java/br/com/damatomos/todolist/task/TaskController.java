package br.com.damatomos.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.damatomos.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  
  @Autowired
  private ITaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel task, HttpServletRequest request)
  {
    UUID userID = (UUID) request.getAttribute("userId");
    task.setUserID(userID);
    System.out.println("UserID: " + userID);
    var currentDate = LocalDateTime.now();
    if (currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt()))
    {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
             .body("A data de início / término deve ser maior que a data atual.");
    }

    if (task.getStartAt().isAfter(task.getEndAt()))
    {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
             .body("A data de início deve ser menor que a data de término.");
    }

    var taskData = this.taskRepository.save(task);
    return ResponseEntity.status(HttpStatus.CREATED).body(taskData);
  }

  @GetMapping("/")
  public List<TaskModel> find(HttpServletRequest request)
  {
    UUID userID = (UUID) request.getAttribute("userId");
    return this.taskRepository.findByUserID(userID);
  }

  @PutMapping("/{id}")
  public TaskModel update(@RequestBody TaskModel task, @PathVariable UUID id, HttpServletRequest request)
  {
    var currentTask = this.taskRepository.findById(id).orElse(null);

    Utils.copyNonNullProperties(task, currentTask);

    return this.taskRepository.save(task);
  }

}
