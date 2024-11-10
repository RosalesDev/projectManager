package com.st.project_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.project_manager.entity.Project;
import com.st.project_manager.entity.UserPerson;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

  List<Project> findByManager(UserPerson userPerson);
}
