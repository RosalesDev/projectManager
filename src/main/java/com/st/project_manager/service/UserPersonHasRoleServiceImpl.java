package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.st.project_manager.dto.UserPersonHasRoleDTO;
import com.st.project_manager.entity.UserPersonHasRole;
import com.st.project_manager.repository.UserPersonHasRoleRepository;

@Service
public class UserPersonHasRoleServiceImpl implements UserPersonHasRoleService {

  private final UserPersonHasRoleRepository repository;
  private final ModelMapper modelMapper;

  public UserPersonHasRoleServiceImpl(UserPersonHasRoleRepository repository, ModelMapper modelMapper) {
    this.repository = repository;
    this.modelMapper = modelMapper;
  }

  @Override
  public Optional<UserPersonHasRoleDTO> createUserRole(UserPersonHasRoleDTO userPersonHasRoleDTO) {
    if (userPersonHasRoleDTO == null) {
      return Optional.empty();
    }
    UserPersonHasRole entity = modelMapper.map(userPersonHasRoleDTO, UserPersonHasRole.class);
    UserPersonHasRole savedEntity = repository.save(entity);
    return Optional.of(modelMapper.map(savedEntity, UserPersonHasRoleDTO.class));
  }

  @Override
  public List<UserPersonHasRoleDTO> getAllUserRoles() {
    return repository.findAll()
        .stream()
        .map(entity -> modelMapper.map(entity, UserPersonHasRoleDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public Optional<UserPersonHasRoleDTO> getUserRoleById(Integer id) {
    return repository.findById(id)
        .map(entity -> modelMapper.map(entity, UserPersonHasRoleDTO.class));
  }

  @Override
  public Optional<UserPersonHasRoleDTO> updateUserRole(Integer id, UserPersonHasRoleDTO userPersonHasRoleDTO) {
    if (!repository.existsById(id)) {
      return Optional.empty();
    }
    UserPersonHasRole entityToUpdate = modelMapper.map(userPersonHasRoleDTO, UserPersonHasRole.class);
    entityToUpdate.setId(id);
    UserPersonHasRole updatedEntity = repository.save(entityToUpdate);
    return Optional.of(modelMapper.map(updatedEntity, UserPersonHasRoleDTO.class));
  }

  @Override
  public Optional<String> deleteRoleById(Integer id) {
    if (!repository.existsById(id)) {
      return Optional.empty();
    }
    repository.deleteById(id);
    return Optional.of("Se ha eliminado el rol con ID " + id);
  }

  @Override
  public Optional<UserPersonHasRoleDTO> getUserRoleByUserPersonId(Integer id) {
    UserPersonHasRole userRole = repository.findByUserPersonId(id).get();
    return Optional.of(modelMapper.map(userRole, UserPersonHasRoleDTO.class));
  }
}
