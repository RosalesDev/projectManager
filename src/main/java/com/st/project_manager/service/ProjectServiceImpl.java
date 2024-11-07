package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.project_manager.dto.ProjectDTO;
import com.st.project_manager.entity.Project;
import com.st.project_manager.exception.ProjectAlreadyHasManagerException;
import com.st.project_manager.repository.ProjectRepository;

import constant.ProjectStatus;

@Service
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;
  private final ModelMapper modelMapper;

  public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
    this.projectRepository = projectRepository;
    this.modelMapper = modelMapper;
  }

  @Transactional
  @Override
  public Optional<ProjectDTO> createProject(ProjectDTO projectDTO) {
    Project newProject = modelMapper.map(projectDTO, Project.class);
    ProjectDTO savedProject = modelMapper.map(projectRepository.save(newProject), ProjectDTO.class);
    return Optional.of(savedProject);
  }

  @Transactional(readOnly = true)
  @Override
  public List<ProjectDTO> getAllProject() {
    List<ProjectDTO> projectLisDto = modelMapper.map(projectRepository.findAll(), new TypeToken<List<ProjectDTO>>() {
    }.getType());
    return projectLisDto;
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<ProjectDTO> getProjectById(Integer id) {
    Optional<ProjectDTO> projectOptional = modelMapper.map(projectRepository.findById(id),
        new TypeToken<Optional<ProjectDTO>>() {
        }.getType());
    return projectOptional;
  }

  // TODO: continuar quí!!!!
  @Transactional
  @Override
  public Optional<ProjectDTO> updateProject(Integer id, ProjectDTO projectDTO) {
    Optional<Project> projectOptional = projectRepository.findById(id);
    if (projectOptional.isPresent()) {
      Boolean hasManager = projectOptional.get().getUserPerson() != null;
      if (projectDTO.getManagerId() != null && hasManager) {
        Integer currentManagerId = projectOptional.get().getUserPerson().getId();
        if (!projectDTO.getManagerId().equals(currentManagerId)) {
          throw new ProjectAlreadyHasManagerException("El proyecto ya tiene un gestor asignado.");
        }
      }
    }
    return Optional.empty();
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
