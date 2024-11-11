package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.project_manager.dto.ProjectDTO;
import com.st.project_manager.entity.Project;
import com.st.project_manager.entity.UserPerson;
import com.st.project_manager.exception.ProjectAlreadyHasManagerException;
import com.st.project_manager.exception.ResourceNotFoundException;
import com.st.project_manager.mapper.ProjectMapper;
import com.st.project_manager.repository.ProjectRepository;
import com.st.project_manager.repository.UserPersonRepository;

import constant.ProjectStatus;

@Service
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;
  private final UserPersonRepository userPersonRepository;
  private final ProjectMapper projectMapper;

  public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper,
      UserPersonRepository userPersonRepository) {
    this.projectRepository = projectRepository;
    this.projectMapper = projectMapper;
    this.userPersonRepository = userPersonRepository;
  }

  @Transactional
  @Override
  public Optional<ProjectDTO> createProject(ProjectDTO projectDTO) {
    Project newProject = projectMapper.toEntity(projectDTO);
    ProjectDTO savedProject = projectMapper.toDTO(projectRepository.save(newProject));
    return Optional.of(savedProject);
  }

  @Override
  public List<ProjectDTO> getAllProject() {
    List<ProjectDTO> projectLisDto = projectMapper.toProjectDTOList(projectRepository.findAll());
    return projectLisDto;
  }

  @Override
  public Optional<ProjectDTO> getProjectById(Integer id) {
    if (id == null || id < 0) {
      throw new IllegalArgumentException("El ID no es válido.");
    }
    Optional<Project> project = projectRepository.findById(id);
    if (project.isEmpty()) {
      throw new ResourceNotFoundException("No se encontró un proyecto con el ID: " + id);
    }
    return Optional.of(projectMapper.toDTO(project.get()));
  }

  @Transactional
  @Override
  public Optional<ProjectDTO> updateProject(Integer id, ProjectDTO projectDTO) {
    if (id == null || id < 0) {
      throw new IllegalArgumentException("El ID no es válido.");
    }
    Optional<Project> projectOptional = projectRepository.findById(id);

    if (projectOptional.isEmpty()) {
      throw new ResourceNotFoundException("No se encontró un proyecto con el ID: " + id);
    }

    Boolean hasManager = projectOptional.get().getUserPerson() != null;
    if (projectDTO.getManagerId() != null && hasManager) {
      Integer currentManagerId = projectOptional.get().getUserPerson().getId();
      if (!projectDTO.getManagerId().equals(currentManagerId)) {
        throw new ProjectAlreadyHasManagerException("No es posible modificar el manager del proyecto.");
      }
    }
    ProjectDTO updatedProjectDto = projectMapper.toDTO(projectOptional.get());

    if (projectDTO.getName() != null && !projectDTO.getName().isEmpty() && !projectDTO.getName().isBlank()) {
      updatedProjectDto.setName(projectDTO.getName());
    }

    if (projectDTO.getDescription() != null && !projectDTO.getDescription().isEmpty()
        && !projectDTO.getDescription().isBlank()) {
      updatedProjectDto.setDescription(projectDTO.getDescription());
    }

    if (projectDTO.getStatus() != null) {
      try {
        ProjectStatus status = projectDTO.getStatus();
        updatedProjectDto.setStatus(status);
      } catch (IllegalArgumentException e) {
        System.out.println("El estado proporcionado no es válido");
      }
    }
    Project savedProject = projectRepository.save(projectMapper.toEntity(updatedProjectDto));
    return Optional.of(projectMapper.toDTO(savedProject));
  }

  @Override
  public List<ProjectDTO> getAllProjectByManager(Integer id) {
    if (id == null || id < 0) {
      throw new IllegalArgumentException("El id no se válido.");
    }
    Optional<UserPerson> userPerson = userPersonRepository.findById(id);
    if (userPerson.isEmpty()) {
      throw new ResourceNotFoundException("No se encontró un usuario con ID: " + id);
    }
    List<Project> projectOpt = projectRepository.findByManager(userPerson.get());
    if (projectOpt.isEmpty()) {
      throw new ResourceNotFoundException("El usuario con ID: " + id + " no tiene proyectos a cargo.");
    }
    return projectMapper.toProjectDTOList(projectOpt);
  }

  @Transactional
  @Override
  public Optional<ProjectDTO> deleteProject(Integer id) {
    Optional<Project> projectOptional = projectRepository.findById(id);
    projectOptional.ifPresent(project -> {
      project.setStatus(ProjectStatus.CANCELED);
    });
    return Optional.empty();
  }

}
