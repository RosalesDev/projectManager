package com.st.project_manager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st.project_manager.dto.ProjectDTO;
import com.st.project_manager.dto.ProjectHasUserPersonDTO;
import com.st.project_manager.service.ProjectHasUserPersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/project-user")
public class ProjectHasUserPersonController {

  ProjectHasUserPersonService phupService;

  public ProjectHasUserPersonController(ProjectHasUserPersonService phupService) {
    this.phupService = phupService;
  }

  @PostMapping("/create")
  public Optional<ProjectHasUserPersonDTO> createProjectHasUserPerson(@RequestBody ProjectHasUserPersonDTO phupDto) {

    // Optional<ProjectHasUserPersonDTO> phup =
    // phupService.createProjectHasUserPerson(projectId, userPersonId);
    return phupService.createProjectHasUserPerson(phupDto);
  }

  @GetMapping("/by-user")
  public List<ProjectDTO> getProjectListByUserPersonId(@RequestParam Integer projectId) {
    return phupService.getAllProjectHasUserPersonByPersonId(projectId);
  }

}
