package com.st.project_manager.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.st.project_manager.dto.TaskDTO;

public interface TaskService {

  Optional<TaskDTO> createTask(TaskDTO TaskDTO);

  List<TaskDTO> getAllTasks();

  Optional<TaskDTO> getTaskById(Integer id);

  List<TaskDTO> getTaskByPersonId(Integer id);

  Map<String, Object> countTaskByPersonId(Integer id);

  List<TaskDTO> getTaskByProjectId(Integer id);

  Map<String, Object> countPendingTaskByProjectId(Integer projectId);

  Map<String, Object> findStartedByProjectId(Integer projectId);

  Map<String, Object> findCompletedByProjectId(Integer projectId);

  List<TaskDTO> searchByTitleOrStatus(String title, String status);

  Optional<TaskDTO> updateTask(Integer id, TaskDTO TaskDTO);

  ResponseEntity<String> deleteTaskById(Integer id);
}
