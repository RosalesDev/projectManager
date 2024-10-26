package com.st.project_manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.st.project_manager.Dto.StepDTO;
import com.st.project_manager.Entity.Step;

@Mapper
public interface StepMapper {
	StepMapper INSTANCE = Mappers.getMapper(StepMapper.class);
	
	StepDTO toDto(Step step);
	Step toEntity(StepDTO stepDto);
}
