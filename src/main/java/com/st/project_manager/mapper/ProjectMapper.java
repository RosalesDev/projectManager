package com.st.project_manager.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import com.st.project_manager.dto.ProjectDTO;
import com.st.project_manager.entity.Project;

@Component
public class ProjectMapper {
  private final ModelMapper modelMapper;

  public ProjectMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public ProjectDTO toDTO(Project project) {
    return modelMapper.map(project, ProjectDTO.class);
  }

  public Project toEntity(ProjectDTO projectDto) {
    return modelMapper.map(projectDto, Project.class);
  }

  public List<ProjectDTO> toProjectDTOList(List<Project> projectList) {
    return modelMapper.map(projectList, new TypeToken<List<ProjectDTO>>() {
    }.getType());
  }
}
