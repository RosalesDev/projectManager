package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import com.st.project_manager.dto.CommentDTO;

public interface CommentService {

  Optional<CommentDTO> createComment(CommentDTO commentDTO);

  List<CommentDTO> getAllComments();

  Optional<CommentDTO> getCommentById(Integer id);

  Optional<CommentDTO> updateComment(Integer id, CommentDTO commentDTO);

  Optional<CommentDTO> deleteComment(Integer id);

}
