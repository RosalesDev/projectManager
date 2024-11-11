package com.st.project_manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st.project_manager.dto.TaskDTO;
import com.st.project_manager.service.TaskService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping("/create")
  public Optional<TaskDTO> createTask(@RequestBody TaskDTO taskDto) {
    return taskService.createTask(taskDto);
  }

  @PutMapping("/update/{id}")
  public Optional<TaskDTO> updateTask(@PathVariable Integer id, @RequestBody TaskDTO taskDto) {
    return taskService.updateTask(id, taskDto);
  }

  @GetMapping("/by-project/{id}")
  public List<TaskDTO> getAllTaskByProjectId(@PathVariable Integer id) {
    return taskService.getTaskByProjectId(id);
  }

  @GetMapping("/by-user/{id}")
  public List<TaskDTO> getAllTaskByUserPerson(@PathVariable Integer id) {
    return taskService.getTaskByPersonId(id);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteTask(@PathVariable Integer id) {
    return taskService.deleteTaskById(id);
  }

}
