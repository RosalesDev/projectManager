package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.st.project_manager.dto.ProjectDTO;
import com.st.project_manager.dto.ProjectHasUserPersonDTO;

@Service
public interface ProjectHasUserPersonService {

  Optional<ProjectHasUserPersonDTO> createProjectHasUserPerson(ProjectHasUserPersonDTO phupDto);

  List<ProjectDTO> getAllProjectHasUserPersonByPersonId(Integer personId);

  Optional<ProjectHasUserPersonDTO> getProjectHasUserPersonByProjectId(Integer projectId);

  Optional<ProjectHasUserPersonDTO> updateProjectHasUserPerson(Integer id,
      ProjectHasUserPersonDTO ProjectHasUserPersonDTO);

  void deleteProjectHasUserPerson(Integer id);
}
