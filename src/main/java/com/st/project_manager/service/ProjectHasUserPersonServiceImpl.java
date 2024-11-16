package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.project_manager.dto.ProjectDTO;
import com.st.project_manager.dto.ProjectHasUserPersonDTO;
import com.st.project_manager.entity.Project;
import com.st.project_manager.entity.ProjectHasUserPerson;
import com.st.project_manager.exception.ResourceNotFoundException;
import com.st.project_manager.mapper.ProjectHasUserPersonMapper;
import com.st.project_manager.mapper.ProjectMapper;
import com.st.project_manager.repository.ProjectHasUserPersonRepository;

@Service
public class ProjectHasUserPersonServiceImpl implements ProjectHasUserPersonService {

  private final ProjectHasUserPersonRepository repository;
  private final ProjectHasUserPersonMapper phupMapper;
  private final ProjectMapper projectMapper;

  public ProjectHasUserPersonServiceImpl(ProjectHasUserPersonRepository repository,
      ProjectHasUserPersonMapper phupMapper, ProjectMapper projectMapper) {
    this.repository = repository;
    this.phupMapper = phupMapper;
    this.projectMapper = projectMapper;
  }

  @Override
  @Transactional
  public Optional<ProjectHasUserPersonDTO> createProjectHasUserPerson(ProjectHasUserPersonDTO phupDto) {
    ProjectHasUserPerson entity = phupMapper.toEntity(phupDto);
    ProjectHasUserPerson savedEntity = repository.save(entity);
    return Optional.of(phupMapper.toDTO(savedEntity));
  }

  @Override
  public List<ProjectDTO> getAllProjectByPersonId(Integer personId) {
    List<Project> entities = repository.findProjectsByUserPersonId(personId);
    return projectMapper.toProjectDTOList(entities);
  }

  @Override
  public Optional<ProjectHasUserPersonDTO> getProjectHasUserPersonByProjectId(Integer projectId) {
    return repository.findByProjectId(projectId)
        .map(phupMapper::toDTO);
  }

  @Override
  @Transactional
  public Optional<ProjectHasUserPersonDTO> updateProjectHasUserPerson(Integer id, ProjectHasUserPersonDTO dto) {
    return repository.findById(id).map(existingEntity -> {
      existingEntity.getProject().setId(dto.getProjectId());
      ;
      existingEntity.getUserPerson().setId(dto.getUserPersonId());
      ProjectHasUserPerson updatedEntity = repository.save(existingEntity);
      return phupMapper.toDTO(updatedEntity);
    });
  }

  @Override
  @Transactional
  public void deleteProjectHasUserPerson(Integer id) {
    if (id == null || id < 0) {
      throw new IllegalArgumentException("El ID no es válido.");
    }
    Optional<ProjectHasUserPerson> phup = repository.findById(id);

    if (phup.isEmpty()) {
      throw new ResourceNotFoundException("El ID: " + id + "no existe.");
    }
    repository.delete(phup.get());
  }

  @Override
  public Optional<Integer> countAllProjectByUserPersonId(Integer id) {
    if (id == null || id < 0) {
      throw new IllegalArgumentException("El ID no es válido.");
    }
    Optional<Integer> count = repository.countAllProjectByUserPersonId(id);

    if (count.isEmpty() || count.get() == 0) {
      throw new ResourceNotFoundException("No se encontraron proyectos para el usuario con ID: " + id);
    }

    return count;
  }

  @Override
  public Boolean isUserAssignedToProject(Integer userId, Integer projectId) {
    return repository.existsByUserPersonIdAndProjectId(userId, projectId);
  }

}
