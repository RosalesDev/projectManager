package com.st.project_manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.st.project_manager.Dto.UserPersonHasProjectDTO;
import com.st.project_manager.Entity.UserPersonHasProject;

@Mapper
public interface UserPersonHasProjectMapper {
	UserPersonHasProjectMapper INTANCE = Mappers.getMapper(UserPersonHasProjectMapper.class);
	
	UserPersonHasProjectDTO toDto(UserPersonHasProject userPersonHasProject);
	UserPersonHasProject toEntity(UserPersonHasProjectDTO userPersonHasProjectDto);
}
