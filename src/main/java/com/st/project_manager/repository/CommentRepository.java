package com.st.project_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.project_manager.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

  List<Comment> findByUserPersonId(Integer id);

  List<Comment> findByTaskId(Integer id);

  List<Comment> findByStepId(Integer id);

}
