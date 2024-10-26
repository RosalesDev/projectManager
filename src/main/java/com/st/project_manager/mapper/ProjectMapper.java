package com.st.project_manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.st.project_manager.Dto.ProjectDTO;
import com.st.project_manager.Entity.Project;

@Mapper
public interface ProjectMapper {
	ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);
	
	ProjectDTO toDto(Project project);
	Project toEntity(ProjectDTO projectDto);
}
