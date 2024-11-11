package com.st.project_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.project_manager.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
