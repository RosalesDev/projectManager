package com.st.project_manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.st.project_manager.entity.Project;
import com.st.project_manager.entity.ProjectHasUserPerson;

@Repository
public interface ProjectHasUserPersonRepository extends JpaRepository<ProjectHasUserPerson, Integer> {

  @Query("SELECT phup.project FROM ProjectHasUserPerson phup WHERE phup.userPerson.id = :personId")
  List<Project> findProjectsByUserPersonId(@Param("personId") Integer personId);

  @Query("SELECT COUNT(phup.project) FROM ProjectHasUserPerson phup WHERE phup.userPerson.id = :personId")
  Optional<Integer> countAllProjectByUserPersonId(Integer personId);

  @Query("SELECT phup.project FROM ProjectHasUserPerson phup WHERE phup.project.id = :projectId")
  Optional<ProjectHasUserPerson> findByProjectId(Integer projectId);

  Boolean existsByUserPersonIdAndProjectId(Integer userId, Integer projectId);

}
