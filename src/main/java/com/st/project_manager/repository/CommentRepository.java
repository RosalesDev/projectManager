package com.st.project_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.st.project_manager.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
