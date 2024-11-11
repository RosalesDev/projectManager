package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.project_manager.dto.RoleDTO;
import com.st.project_manager.entity.Role;
import com.st.project_manager.exception.InvalidIdException;
import com.st.project_manager.exception.ResourceNotFoundException;
import com.st.project_manager.mapper.RoleMapper;
import com.st.project_manager.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;
  private final RoleMapper roleMapper;

  public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
    this.roleRepository = roleRepository;
    this.roleMapper = roleMapper;
  }

  @Override
  @Transactional
  public Optional<RoleDTO> createRole(RoleDTO roleDTO) {
    Role role = roleMapper.toEntity(roleDTO);
    Role savedRole = roleRepository.save(role);
    return Optional.of(roleMapper.toDTO(savedRole));
  }

  @Override
  public List<RoleDTO> getAllRoles() {
    return roleMapper.toDTOList(roleRepository.findAll());
  }

  @Override
  public Optional<RoleDTO> getRoleById(Integer id) {
    if (id == null || id < 0) {
      throw new InvalidIdException();
    }
    Optional<Role> role = roleRepository.findById(id);

    if (role.isEmpty()) {
      throw new ResourceNotFoundException("No exite el role con ID: " + id);
    }

    return Optional.of(roleMapper.toDTO(role.get()));
  }

  @Override
  @Transactional
  public Optional<RoleDTO> updateRole(Integer id, RoleDTO roleDTO) {
    if (id == null || id < 0) {
      throw new InvalidIdException();
    }
    Optional<Role> role = roleRepository.findById(id);

    if (role.isEmpty()) {
      throw new ResourceNotFoundException("No exite el role con ID: " + id);
    }
    RoleDTO updatedRole = roleMapper.toDTO(role.get());

    if (roleDTO.getName() != null && !roleDTO.getName().isEmpty() && !roleDTO.getName().isBlank()) {
      updatedRole.setName(roleDTO.getName());
    }
    if (roleDTO.getDescription() != null && !roleDTO.getDescription().isEmpty()
        && !roleDTO.getDescription().isBlank()) {
      updatedRole.setDescription(roleDTO.getDescription());
    }
    if (roleDTO.getEnable() != null) {
      updatedRole.setEnable(roleDTO.getEnable());
    }
    return Optional.of(updatedRole);

  }

  @Override
  @Transactional
  public ResponseEntity<String> deleteRoleById(Integer id) {
    if (id == null || id < 0) {
      throw new InvalidIdException();
    }
    Optional<Role> role = roleRepository.findById(id);

    if (role.isEmpty()) {
      throw new ResourceNotFoundException("No exite el role con ID: " + id);
    }
    roleRepository.deleteById(id);
    return ResponseEntity.ok("El role con ID " + id + " fue eliminado con éxito.");
  }
}
