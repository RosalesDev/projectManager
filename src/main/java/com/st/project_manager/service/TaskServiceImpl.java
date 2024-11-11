package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.st.project_manager.dto.TaskDTO;
import com.st.project_manager.entity.Project;
import com.st.project_manager.entity.Task;
import com.st.project_manager.entity.UserPerson;
import com.st.project_manager.exception.InvalidIdException;
import com.st.project_manager.exception.ResourceNotFoundException;
import com.st.project_manager.mapper.TaskMapper;
import com.st.project_manager.repository.ProjectRepository;
import com.st.project_manager.repository.TaskRepository;
import com.st.project_manager.repository.UserPersonRepository;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final UserPersonRepository userPersonRepository;
  private final ProjectRepository projectRepository;
  private final TaskMapper taskMapper;

  public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper,
      UserPersonRepository userPersonRepository, ProjectRepository projectRepository) {
    this.taskRepository = taskRepository;
    this.taskMapper = taskMapper;
    this.userPersonRepository = userPersonRepository;
    this.projectRepository = projectRepository;
  }

  @Override
  public Optional<TaskDTO> createTask(TaskDTO TaskDTO) {
    Task task = taskRepository.save(taskMapper.toEntity(TaskDTO));

    return Optional.of(taskMapper.toDTO(task));
  }

  @Override
  public List<TaskDTO> getAllTasks() {
    return taskMapper.toDTOList(taskRepository.findAll());
  }

  @Override
  public Optional<TaskDTO> getTaskById(Integer id) {
    if (id == null || id < 0) {
      throw new InvalidIdException();
    }
    Optional<Task> task = taskRepository.findById(id);
    if (task.isEmpty()) {
      throw new ResourceNotFoundException("La tarea con ID: " + id + " no existe.");
    }
    return Optional.of(taskMapper.toDTO(task.get()));
  }

  @Override
  public List<TaskDTO> getTaskByPersonId(Integer id) {
    if (id == null || id < 0) {
      throw new InvalidIdException();
    }

    Optional<UserPerson> userPerson = userPersonRepository.findById(id);

    if (userPerson.isEmpty()) {
      throw new ResourceNotFoundException("El usuario con ID: " + id + " no existe.");
    }

    List<Task> tasks = taskRepository.findAllByUserPersonId(userPerson.get());

    return taskMapper.toDTOList(tasks);
  }

  @Override
  public Optional<TaskDTO> updateTask(Integer id, TaskDTO TaskDTO) {
    if (id == null || id < 0) {
      throw new InvalidIdException();
    }

    Optional<Task> task = taskRepository.findById(id);

    if (task.isEmpty()) {
      throw new ResourceNotFoundException("La tarea con ID: " + id + " no existe.");
    }

    TaskDTO updatedTask = taskMapper.toDTO(taskRepository.save(task.get()));
    return Optional.of(updatedTask);
  }

  @Override
  public ResponseEntity<String> deleteTaskById(Integer id) {
    if (id == null || id < 0) {
      throw new InvalidIdException();
    }

    Optional<Task> task = taskRepository.findById(id);

    if (task.isEmpty()) {
      throw new ResourceNotFoundException("La tarea con ID: " + id + " no existe.");
    }
    taskRepository.deleteById(id);

    return ResponseEntity.ok("La tarea con ID " + id + " fue eliminada con éxito.");
  }

  @Override
  public List<TaskDTO> getTaskByProjectId(Integer id) {
    if (id == null || id < 0) {
      throw new InvalidIdException();
    }

    Optional<Project> project = projectRepository.findById(id);

    if (project.isEmpty()) {
      throw new ResourceNotFoundException("El proyecto con ID: " + id + " no existe.");
    }

    List<Task> tasks = taskRepository.findAllByProjectId(project.get());

    return taskMapper.toDTOList(tasks);
  }

}
