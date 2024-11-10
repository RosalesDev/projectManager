package com.st.project_manager.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.st.project_manager.dto.StepDTO;
import com.st.project_manager.entity.Step;

@Component
public class StepMapper {
  private final ModelMapper modelMapper;

  public StepMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public StepDTO toDTO(Step step) {
    return modelMapper.map(step, StepDTO.class);
  }

  public Step toEntity(StepDTO stepDto) {
    return modelMapper.map(stepDto, Step.class);
  }
}
