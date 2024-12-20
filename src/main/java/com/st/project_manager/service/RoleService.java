package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.st.project_manager.dto.RoleDTO;

public interface RoleService {
  Optional<RoleDTO> createRole(RoleDTO RoleDTO);

  List<RoleDTO> getAllRoles();

  Optional<RoleDTO> getRoleById(Integer id);

  Optional<RoleDTO> updateRole(Integer id, RoleDTO RoleDTO);

  ResponseEntity<String> deleteRoleById(Integer id);
}
