package com.st.project_manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.st.project_manager.Dto.CommentDTO;
import com.st.project_manager.Entity.Comment;

@Mapper
public interface CommentMapper {
	CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
	
	CommentDTO toDto(Comment comment);
	Comment toEntity(CommentDTO commentDto);
}
