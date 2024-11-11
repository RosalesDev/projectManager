package com.st.project_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.project_manager.entity.Project;
import com.st.project_manager.entity.Task;
import com.st.project_manager.entity.UserPerson;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
  List<Task> findAllByUserPersonId(UserPerson userPerson);

  List<Task> findAllByProjectId(Project project);

}
