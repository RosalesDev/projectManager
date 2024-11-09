package com.st.project_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.st.project_manager.entity.Step;

@Repository
public interface StepRepository extends JpaRepository<Step, Integer> {

  // @Query("SELECT st.task FROM Step st WHERE st.task.id = :taskId")
  List<Step> findAllByTaskId(@Param("taskId") Integer taskId);
}
