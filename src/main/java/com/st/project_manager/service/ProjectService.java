package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import com.st.project_manager.dto.ProjectDTO;

public interface ProjectService {
  Optional<ProjectDTO> createProject(ProjectDTO projectDTO);

  List<ProjectDTO> getAllProject();

  List<ProjectDTO> getAllProjectByManager(Integer id);

  Optional<ProjectDTO> getProjectById(Integer id);

  Optional<ProjectDTO> updateProject(Integer id, ProjectDTO projectDTO);

  Optional<ProjectDTO> deleteProject(Integer id);
}
