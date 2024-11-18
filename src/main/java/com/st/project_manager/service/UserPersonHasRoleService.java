package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import com.st.project_manager.dto.UserPersonHasRoleDTO;

public interface UserPersonHasRoleService {

  Optional<UserPersonHasRoleDTO> createUserRole(UserPersonHasRoleDTO userUserPersonHasRoleDTO);

  List<UserPersonHasRoleDTO> getAllUserRoles();

  Optional<UserPersonHasRoleDTO> getUserRoleById(Integer id);

  Optional<UserPersonHasRoleDTO> getUserRoleByUserPersonId(Integer id);

  Optional<UserPersonHasRoleDTO> updateUserRole(Integer id, UserPersonHasRoleDTO UserPersonHasRoleDTO);

  Optional<String> deleteRoleById(Integer id);
}
