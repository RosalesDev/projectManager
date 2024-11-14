package com.st.project_manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.st.project_manager.entity.Project;
import com.st.project_manager.entity.Task;
import com.st.project_manager.entity.UserPerson;

import constant.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
  List<Task> findAllByUserPersonId(UserPerson userPerson);

  List<Task> findAllByProjectId(Project project);

  @Query("SELECT COUNT(t) FROM Task t WHERE t.userPerson.id = :id")
  Optional<Integer> countAllByPersonId(Integer id);

  List<Task> findByTitleContainingIgnoreCaseOrStatus(String title, TaskStatus status);

}
