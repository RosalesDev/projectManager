package com.st.project_manager.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import com.st.project_manager.dto.ProjectHasUserPersonDTO;
import com.st.project_manager.entity.ProjectHasUserPerson;

@Component
public class ProjectHasUserPersonMapper {
  private final ModelMapper modelMapper;

  public ProjectHasUserPersonMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public ProjectHasUserPersonDTO toDTO(ProjectHasUserPerson phup) {
    return modelMapper.map(phup, ProjectHasUserPersonDTO.class);
  }

  public ProjectHasUserPerson toEntity(ProjectHasUserPersonDTO phupDTO) {
    return modelMapper.map(phupDTO, ProjectHasUserPerson.class);
  }

  public List<ProjectHasUserPersonDTO> toDTOList(List<ProjectHasUserPerson> phupList) {
    return modelMapper.map(phupList, new TypeToken<List<ProjectHasUserPersonDTO>>() {
    }.getType());
  }
}
