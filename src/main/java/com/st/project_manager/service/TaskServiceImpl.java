package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.st.project_manager.dto.ProjectDTO;
import com.st.project_manager.dto.TaskDTO;
import com.st.project_manager.dto.UserPersonDTO;
import com.st.project_manager.entity.Project;
import com.st.project_manager.entity.Task;
import com.st.project_manager.entity.UserPerson;
import com.st.project_manager.exception.InvalidIdException;
import com.st.project_manager.exception.ResourceNotFoundException;
import com.st.project_manager.exception.UserNotInProjectException;

import com.st.project_manager.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final UserPersonService userPersonService;
  private final ProjectService projectService;
  private final ModelMapper modelMapper;
  private final ProjectHasUserPersonService phupService;

  public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper,
      UserPersonService userPersonService, ProjectService projectService,
      ProjectHasUserPersonService phupService) {
    this.taskRepository = taskRepository;
    this.modelMapper = modelMapper;
    this.userPersonService = userPersonService;
    this.projectService = projectService;
    this.phupService = phupService;
  }

  @Override
  public Optional<TaskDTO> createTask(TaskDTO taskDTO) {

    if (taskDTO.getUserPersonId() != null) {
      if (!phupService.userIsInProject(taskDTO.getUserPersonId(), taskDTO.getProjectId())) {
        throw new UserNotInProjectException();
      }
    }
    Task task = taskRepository.save(modelMapper.map(taskDTO, Task.class));
    System.out.println(task.toString());

    return Optional.of(modelMapper.map(task, TaskDTO.class));
  }

  @Override
  public List<TaskDTO> getAllTasks() {
    return modelMapper.map(taskRepository.findAll(), new TypeToken<List<TaskDTO>>() {
    }.getType());
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
    return Optional.of(modelMapper.map(task.get(), TaskDTO.class));
  }

  @Override
  public List<TaskDTO> getTaskByPersonId(Integer id) {
    if (id == null || id < 0) {
      throw new InvalidIdException();
    }

    Optional<UserPersonDTO> userPersonOpt = userPersonService.getUserPersonById(id);

    if (userPersonOpt.isEmpty()) {
      throw new ResourceNotFoundException("El usuario con ID: " + id + " no existe.");
    }

    UserPerson userPerson = modelMapper.map(userPersonOpt.get(), UserPerson.class);

    List<Task> tasks = taskRepository.findAllByUserPersonId(userPerson);

    return modelMapper.map(tasks, new TypeToken<List<Task>>() {
    }.getType());
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

    TaskDTO updatedTask = modelMapper.map(taskRepository.save(task.get()), TaskDTO.class);
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

    Optional<ProjectDTO> project = projectService.getProjectById(id);

    if (project.isEmpty()) {
      throw new ResourceNotFoundException("El proyecto con ID: " + id + " no existe.");
    }

    List<Task> tasks = taskRepository.findAllByProjectId(modelMapper.map(project.get(), Project.class));

    return modelMapper.map(tasks, new TypeToken<List<Task>>() {
    }.getType());
  }

  @Override
  public Optional<Integer> countTaskByPersonId(Integer id) {
    if (id == null || id < 0) {
      throw new InvalidIdException();
    }
    Optional<UserPersonDTO> userPerson = userPersonService.getUserPersonById(id);

    if (userPerson.isEmpty()) {
      throw new ResourceNotFoundException("El usuario con ID: " + id + " no existe.");
    }

    return taskRepository.countAllByPersonId(id);
  }

}
