package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.project_manager.dto.ProjectDTO;
import com.st.project_manager.dto.ProjectHasUserPersonDTO;
import com.st.project_manager.entity.Project;
import com.st.project_manager.entity.ProjectHasUserPerson;
import com.st.project_manager.repository.ProjectHasUserPersonRepository;

@Service
public class ProjectHasUserPersonServiceImpl implements ProjectHasUserPersonService {

  private final ProjectHasUserPersonRepository repository;
  private final ModelMapper modelMapper;

  public ProjectHasUserPersonServiceImpl(ProjectHasUserPersonRepository repository, ModelMapper modelMapper) {
    this.repository = repository;
    this.modelMapper = modelMapper;
  }

  @Override
  @Transactional
  public Optional<ProjectHasUserPersonDTO> createProjectHasUserPerson(Integer projectId, Integer userPersonId) {
    ProjectHasUserPerson entity = new ProjectHasUserPerson(projectId, userPersonId);
    ProjectHasUserPerson savedEntity = repository.save(entity);
    return Optional.of(modelMapper.map(savedEntity, ProjectHasUserPersonDTO.class));
  }

  @Override
  public List<ProjectDTO> getAllProjectHasUserPersonByPersonId(Integer personId) {
    List<Project> entities = repository.findProjectsByUserPersonId(personId);
    return entities.stream()
        .map(entity -> modelMapper.map(entity, ProjectDTO.class))
        .toList();
  }

  @Override
  public Optional<ProjectHasUserPersonDTO> getProjectHasUserPersonByProjectId(Integer projectId) {
    return repository.findByProjectId(projectId)
        .map(entity -> modelMapper.map(entity, ProjectHasUserPersonDTO.class));
  }

  @Override
  @Transactional
  public Optional<ProjectHasUserPersonDTO> updateProjectHasUserPerson(Integer id, ProjectHasUserPersonDTO dto) {
    return repository.findById(id).map(existingEntity -> {
      existingEntity.setProjectId(dto.getProjectId());
      existingEntity.setUserPersonId(dto.getUserPersonId());
      ProjectHasUserPerson updatedEntity = repository.save(existingEntity);
      return modelMapper.map(updatedEntity, ProjectHasUserPersonDTO.class);
    });
  }

  @Override
  @Transactional
  public Optional<ProjectHasUserPersonDTO> deleteProjectHasUserPerson(Integer id) {
    return repository.findById(id).map(entity -> {
      repository.delete(entity);
      return modelMapper.map(entity, ProjectHasUserPersonDTO.class);
    });
  }

}
