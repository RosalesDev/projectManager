package com.st.project_manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.st.project_manager.dto.ProjectDTO;
import com.st.project_manager.dto.StepDTO;
import com.st.project_manager.dto.TaskDTO;
import com.st.project_manager.dto.UserPersonDTO;
import com.st.project_manager.entity.Project;
import com.st.project_manager.entity.Task;
import com.st.project_manager.entity.UserPerson;
import com.st.project_manager.exception.InvalidIdException;
import com.st.project_manager.exception.ResourceNotFoundException;
import com.st.project_manager.exception.TaskWithoutStepsCompletedException;
import com.st.project_manager.exception.UserNotInProjectException;

import com.st.project_manager.repository.TaskRepository;

import constant.StepStatus;
import constant.TaskStatus;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final UserPersonService userPersonService;
  private final ProjectService projectService;
  private final StepService stepService;
  private final ModelMapper modelMapper;
  private final ProjectHasUserPersonService phupService;

  public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper,
      UserPersonService userPersonService, ProjectService projectService,
      ProjectHasUserPersonService phupService,
      StepService stepService) {
    this.taskRepository = taskRepository;
    this.modelMapper = modelMapper;
    this.userPersonService = userPersonService;
    this.projectService = projectService;
    this.phupService = phupService;
    this.stepService = stepService;
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
  public Optional<TaskDTO> updateTask(Integer id, TaskDTO taskDTO) throws RuntimeException {
    if (id == null || id < 0) {
      throw new InvalidIdException();
    }

    Optional<Task> task = taskRepository.findById(id);

    if (task.isEmpty()) {
      throw new ResourceNotFoundException("La tarea con ID: " + id + " no existe.");
    }

    Task updatedTask = task.get();

    if (taskDTO.getStatus() != null && taskDTO.getStatus().equals(TaskStatus.COMPLETED.toString())) {
      List<StepDTO> stepsDto = stepService.getAllStepByTaskId(id);
      if (stepsDto.isEmpty() || stepsDto.stream().allMatch(step -> step.getStatus().equals(StepStatus.COMPLETED))) {
        updatedTask.setStatus(TaskStatus.COMPLETED);
      }
      throw new TaskWithoutStepsCompletedException();
    }

    if (taskDTO.getUserPersonId() != null) {
      Optional<UserPersonDTO> userPersonOpt = userPersonService.getUserPersonById(taskDTO.getUserPersonId());
      if (userPersonOpt.isEmpty()) {
        throw new ResourceNotFoundException("El usuario con ID: " + taskDTO.getUserPersonId() + "no existe.");
      }
      updatedTask.setUserPerson(modelMapper.map(userPersonOpt.get(), UserPerson.class));
    }
    if (taskDTO.getEndDate() != null) {
      updatedTask.setEndDate(taskDTO.getEndDate());
    }

    if (taskDTO.getTitle() != null) {
      updatedTask.setTitle(taskDTO.getTitle());
    }

    if (taskDTO.getDescription() != null) {
      updatedTask.setDescription(taskDTO.getDescription());
    }

    if (taskDTO.getProjectId() != null) {
      Optional<ProjectDTO> project = projectService.getProjectById(taskDTO.getProjectId());
      if (project.isEmpty()) {
        throw new ResourceNotFoundException("No se encuentra el proyecto con ID:" + taskDTO.getProjectId());
      }
      updatedTask.setProject(modelMapper.map(project.get(), Project.class));
    }

    return Optional.of(modelMapper.map(taskRepository.save(updatedTask), TaskDTO.class));
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

  @Override
  public List<TaskDTO> searchByTitleOrStatus(String title, String status) {
    TaskStatus taskStatus = null;
    if (status != null) {
      try {
        taskStatus = TaskStatus.valueOf(status.toUpperCase());
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("El estado no es válido: " + status);
      }
    }
    List<Task> taskList = taskRepository.findByTitleContainingIgnoreCaseOrStatus(title, taskStatus);
    return modelMapper.map(taskList, new TypeToken<List<TaskDTO>>() {
    }.getType());
  }

  @Override
  public Optional<Integer> countPendingTaskByProjectId(Integer projectId) {
    if (projectId == null || projectId < 0) {
      throw new InvalidIdException();
    }
    return taskRepository.countAllPendingByProjectId(projectId);
  }

  @Override
  public Map<String, Object> findStartedByProjectId(Integer projectId) {
    if (projectId == null || projectId < 0) {
      throw new InvalidIdException();
    }
    List<TaskDTO> taskList = modelMapper.map(taskRepository.findAllStartedByProjectId(projectId),
        new TypeToken<List<TaskDTO>>() {
        }.getType());

    Integer countStartedTask = taskList.size();

    Map<String, Object> response = new HashMap<>();
    response.put("projectId", projectId.toString());
    response.put("countStartedTask", countStartedTask);
    response.put("taskList", taskList);

    return response;
  }

  @Override
  public Map<String, Object> findCompletedByProjectId(Integer projectId) {
    if (projectId == null || projectId < 0) {
      throw new InvalidIdException();
    }
    List<TaskDTO> taskList = modelMapper.map(taskRepository.findAllCompletedByProjectId(projectId),
        new TypeToken<List<TaskDTO>>() {
        }.getType());

    Integer countCompletedTask = taskList.size();

    Map<String, Object> response = new HashMap<>();
    response.put("projectId", projectId);
    response.put("countCompletedTask", countCompletedTask);
    response.put("taskList", taskList);

    return response;
  }

}
