package com.st.project_manager.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import com.st.project_manager.dto.CommentDTO;
import com.st.project_manager.entity.Comment;

@Component
public class CommentMapper {
  private final ModelMapper modelMapper;

  public CommentMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public CommentDTO toDTO(Comment comment) {
    return modelMapper.map(comment, CommentDTO.class);
  }

  public Comment toEntity(CommentDTO CommentDTO) {
    return modelMapper.map(CommentDTO, Comment.class);
  }

  public List<CommentDTO> toDTOList(List<Comment> commentList) {
    return modelMapper.map(commentList, new TypeToken<List<CommentDTO>>() {
    }.getType());
  }
}
