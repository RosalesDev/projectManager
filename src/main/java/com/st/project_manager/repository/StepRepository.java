package com.st.project_manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.st.project_manager.entity.Step;

@Repository
public interface StepRepository extends JpaRepository<Step, Integer> {

  List<Step> findAllByTaskId(Integer taskId);

  @Query("SELECT COUNT(s) FROM Step s WHERE s.status NOT IN ('COMPLETED','DEACTIVE')")
  Optional<Integer> countRemainingStepsByTaskId(Integer taskId);
}
