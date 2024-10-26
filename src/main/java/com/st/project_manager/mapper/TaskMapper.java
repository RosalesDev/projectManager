package com.st.project_manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.st.project_manager.Dto.TaskDTO;
import com.st.project_manager.Entity.Task;

@Mapper
public interface TaskMapper {
	TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
	
	TaskDTO toDto(Task task);
	Task toEntity(TaskDTO taskDto);
}
