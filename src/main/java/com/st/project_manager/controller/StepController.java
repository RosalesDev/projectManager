package com.st.project_manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st.project_manager.dto.StepDTO;
import com.st.project_manager.service.StepService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/step")
public class StepController {

  private final StepService stepService;
  // private final ModelMapper modelMapper;

  public StepController(StepService stepService) {
    this.stepService = stepService;
    // this.modelMapper = modelMapper;
  }

  @PostMapping("/create-step")
  public Optional<StepDTO> postMethodName(@RequestBody StepDTO stepDto) {
    return stepService.createStep(stepDto);
  }

  @GetMapping("/by-task/{taskId}")
  public List<StepDTO> getAllStepByTaskId(@PathVariable Integer taskId) {
    return stepService.getAllStepByTaskId(taskId);
  }

}
