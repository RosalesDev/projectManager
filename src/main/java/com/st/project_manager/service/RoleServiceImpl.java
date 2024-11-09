package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.project_manager.dto.RoleDTO;
import com.st.project_manager.entity.Role;
import com.st.project_manager.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;
  private final ModelMapper modelMapper;

  public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
    this.roleRepository = roleRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  @Transactional
  public Optional<RoleDTO> createRole(RoleDTO roleDTO) {
    Role role = modelMapper.map(roleDTO, Role.class);
    Role savedRole = roleRepository.save(role);
    return Optional.of(modelMapper.map(savedRole, RoleDTO.class));
  }

  @Override
  public List<RoleDTO> getAllRoles() {
    return roleRepository.findAll().stream()
        .map(role -> modelMapper.map(role, RoleDTO.class))
        .toList();
  }

  @Override
  public Optional<RoleDTO> getRoleById(Integer id) {
    return roleRepository.findById(id)
        .map(role -> modelMapper.map(role, RoleDTO.class));
  }

  @Override
  @Transactional
  public Optional<RoleDTO> updateRole(Integer id, RoleDTO roleDTO) {
    return roleRepository.findById(id).map(existingRole -> {
      existingRole.setName(roleDTO.getName());
      existingRole.setDescription(roleDTO.getDescription());
      Role updatedRole = roleRepository.save(existingRole);
      return modelMapper.map(updatedRole, RoleDTO.class);
    });
  }

  @Override
  @Transactional
  public Optional<RoleDTO> deleteRole(Integer id) {
    return roleRepository.findById(id).map(role -> {
      roleRepository.delete(role);
      return modelMapper.map(role, RoleDTO.class);
    });
  }
}
