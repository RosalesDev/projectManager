package com.st.project_manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.st.project_manager.dto.ProjectDTO;
import com.st.project_manager.service.ProjectService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

  private final ProjectService projectService;

  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @PostMapping("/create")
  public Optional<ProjectDTO> createProject(@Valid @RequestBody ProjectDTO projectDto) {
    return projectService.createProject(projectDto);
  }

  @PutMapping("/update/{id}")
  public Optional<ProjectDTO> updateProject(@PathVariable Integer id, @RequestBody ProjectDTO projectDto) {
    return projectService.updateProject(id, projectDto);
  }

  @PutMapping("/set-project-manager")
  public Optional<ProjectDTO> updateProjectManagerId(@RequestParam Integer projectId, @RequestParam Integer managerId) {
    return projectService.updateProjectManagerId(projectId, managerId);
  }

  @GetMapping("/allProjects")
  public List<ProjectDTO> getAllProjects() {
    return projectService.getAllProject();
  }

  @GetMapping("/by-id/{id}")
  public Optional<ProjectDTO> getProjectById(@PathVariable Integer id) {
    return projectService.getProjectById(id);
  }

  @GetMapping("/by-manager/{id}")
  public List<ProjectDTO> getProjectsByUserPerson(@PathVariable Integer id) {
    return projectService.getAllProjectByManager(id);
  }

}
