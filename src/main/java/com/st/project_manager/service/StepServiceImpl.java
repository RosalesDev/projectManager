package com.st.project_manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.project_manager.dto.StepDTO;
import com.st.project_manager.entity.Step;
import com.st.project_manager.exception.ResourceNotFoundException;
import com.st.project_manager.mapper.StepMapper;
import com.st.project_manager.repository.StepRepository;

@Service
public class StepServiceImpl implements StepService {

  private final StepRepository stepRepository;
  private final StepMapper stepMapper;

  public StepServiceImpl(StepRepository stepRepository, StepMapper stepMapper) {
    this.stepRepository = stepRepository;
    this.stepMapper = stepMapper;
  }

  @Override
  @Transactional
  public Optional<StepDTO> createStep(StepDTO stepDTO) {
    Step step = stepMapper.toEntity(stepDTO);
    Step savedStep = stepRepository.save(step);
    return Optional.of(stepMapper.toDTO(savedStep));
  }

  @Override
  public List<StepDTO> getAllStepByTaskId(Integer taskId) {
    if (taskId == null || taskId < 0) {
      throw new IllegalArgumentException("ID no válido.");
    }

    List<Step> steps = stepRepository.findAllByTaskId(taskId);

    return steps.stream()
        .map(stepMapper::toDTO)
        .toList();
  }

  @Override
  public Optional<StepDTO> getStepById(Integer id) {
    if (id == null || id < 0) {
      throw new IllegalArgumentException("ID no válido.");
    }
    Optional<Step> step = stepRepository.findById(id);
    if (step.isEmpty()) {
      throw new ResourceNotFoundException("No se encontro el paso con ID: " + id);
    }
    return step.map(stepMapper::toDTO);
  }

  @Override
  @Transactional
  public Optional<StepDTO> updateStep(Integer id, StepDTO stepDTO) {
    return stepRepository.findById(id).map(existingStep -> {
      existingStep.setName(stepDTO.getName());
      existingStep.setDescription(stepDTO.getDescription());
      Step updatedStep = stepRepository.save(existingStep);
      return stepMapper.toDTO(updatedStep);
    });
  }

  @Override
  @Transactional
  public ResponseEntity<String> deleteStepById(Integer id) {

    if (id == null || id < 0) {
      throw new IllegalArgumentException("ID no válido.");
    }

    Optional<Step> step = stepRepository.findById(id);

    if (step.isEmpty()) {
      throw new ResourceNotFoundException("No se encontro el paso con ID: " + id);
    }

    stepRepository.deleteById(id);

    return ResponseEntity.ok("El paso con ID " + id + " fue eliminado con éxito.");
  }

  @Override
  public Map<String, Object> countRemainingStepsByTaskId(Integer id) {
    if (id == null || id < 0) {
      throw new IllegalArgumentException("ID no válido.");
    }
    Integer countSteps = stepRepository.countRemainingStepsByTaskId(id).get();

    Map<String, Object> response = new HashMap<>();

    response.put("taskId", id);
    response.put("countRemainingSteps", countSteps);

    return response;

  }

}