package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import com.st.project_manager.dto.StepDTO;

public interface StepService {
  Optional<StepDTO> createStep(StepDTO StepDTO);

  List<StepDTO> getAllStepByTaskId(Integer id);

  Optional<StepDTO> getStepById(Integer id);

  Optional<StepDTO> updateStep(Integer id, StepDTO StepDTO);

  void deleteStep(Integer id);
}
