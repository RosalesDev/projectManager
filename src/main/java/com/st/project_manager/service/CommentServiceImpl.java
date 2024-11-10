package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.project_manager.dto.CommentDTO;
import com.st.project_manager.entity.Comment;
import com.st.project_manager.exception.handler.ResourceNotFoundException;
import com.st.project_manager.mapper.CommentMapper;
import com.st.project_manager.repository.CommentRepository;

import constant.CommentStatus;

@Service
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final CommentMapper commentMapper;

  public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
    this.commentRepository = commentRepository;
    this.commentMapper = commentMapper;
  }

  @Override
  @Transactional
  public Optional<CommentDTO> createComment(CommentDTO commentDTO) {
    if (commentDTO.getId() != null || commentDTO.getPersonId() == null || commentDTO.getText() == null) {
      throw new IllegalArgumentException("El comentario no es válido.");
    }
    Comment comment = commentMapper.toEntity(commentDTO);
    Comment savedComment = commentRepository.save(comment);
    return Optional.of(commentMapper.toDTO(savedComment));
  }

  @Override
  public List<CommentDTO> getAllComments() {
    List<Comment> comments = commentRepository.findAll();
    if (comments.isEmpty()) {
      throw new ResourceNotFoundException("No se encontraron comentarios.");
    }
    return commentMapper.toDTOList(comments);
  }

  @Override
  public Optional<CommentDTO> getCommentById(Integer id) {
    if (id == null || id < 0) {
      throw new ResourceNotFoundException("El ID no es válido.");
    }
    Optional<Comment> comment = commentRepository.findById(id);
    if (comment.isEmpty()) {
      throw new ResourceNotFoundException("El comentario con ID: " + id + "no existe");
    }
    return Optional.of(commentMapper.toDTO(comment.get()));
  }

  @Override
  @Transactional
  public Optional<CommentDTO> updateComment(Integer id, CommentDTO commentDTO) {
    Optional<Comment> comment = commentRepository.findById(id);

    if (comment.isPresent()) {
      Comment commentToSave = comment.get();
      commentToSave.setText(commentDTO.getText());
      commentToSave.setStatus(commentDTO.getStatus());
      Comment savedComment = commentRepository.save(commentToSave);
      return Optional.of(commentMapper.toDTO(savedComment));
    }
    return Optional.empty();
  }

  @Override
  @Transactional
  public Optional<CommentDTO> deleteComment(Integer id) {
    if (id == null || id < 0) {
      throw new ResourceNotFoundException("El ID no es válido.");
    }
    Optional<Comment> comment = commentRepository.findById(id);
    if (comment.isEmpty()) {
      throw new ResourceNotFoundException("El comentario con ID: " + id + "no existe");
    }

    comment.get().setStatus(CommentStatus.DELETED);
    return Optional.empty();
  }
}
