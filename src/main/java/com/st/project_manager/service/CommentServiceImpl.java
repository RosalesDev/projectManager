package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.project_manager.dto.CommentDTO;
import com.st.project_manager.entity.Comment;
import com.st.project_manager.repository.CommentRepository;

import constant.CommentStatus;

@Service
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final ModelMapper modelMapper;

  public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
    this.commentRepository = commentRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  @Transactional
  public Optional<CommentDTO> createComment(CommentDTO commentDTO) {
    Comment comment = modelMapper.map(commentDTO, Comment.class);
    Comment savedComment = commentRepository.save(comment);
    return Optional.of(modelMapper.map(savedComment, CommentDTO.class));
  }

  @Override
  public List<CommentDTO> getAllComments() {
    return modelMapper.map(commentRepository.findAll(), new TypeToken<List<CommentDTO>>() {
    }.getType());
  }

  @Override
  public Optional<CommentDTO> getCommentById(Integer id) {
    Comment comment = commentRepository.findById(id).get();
    return Optional.of(modelMapper.map(comment, CommentDTO.class));
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
      return Optional.of(modelMapper.map(savedComment, CommentDTO.class));
    }
    return Optional.empty();
  }

  @Override
  @Transactional
  public Optional<CommentDTO> deleteComment(Integer id) {
    Optional<Comment> comment = commentRepository.findById(id);
    if (comment.isPresent()) {
      Comment commentToDelete = comment.get();

      commentToDelete.setStatus(CommentStatus.DELETED);
      return Optional.empty();
    }
    return Optional.empty();
  }
}
