package com.st.project_manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.st.project_manager.entity.UserPersonHasRole;

public interface UserPersonHasRoleRepository extends JpaRepository<UserPersonHasRole, Integer> {
  Optional<UserPersonHasRole> findByUserPersonId(Integer id);

  Optional<UserPersonHasRole> findByRoleId(Integer id);

}
