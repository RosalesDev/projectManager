package com.st.project_manager.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.project_manager.dto.StepDTO;
import com.st.project_manager.entity.Step;
import com.st.project_manager.repository.StepRepository;

@Service
public class StepServiceImpl implements StepService {

  private final StepRepository stepRepository;
  private final ModelMapper modelMapper;

  public StepServiceImpl(StepRepository stepRepository, ModelMapper modelMapper) {
    this.stepRepository = stepRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  @Transactional
  public Optional<StepDTO> createStep(StepDTO stepDTO) {
    Step step = modelMapper.map(stepDTO, Step.class);
    Step savedStep = stepRepository.save(step);
    return Optional.of(modelMapper.map(savedStep, StepDTO.class));
  }

  @Override
  public List<StepDTO> getAllStepByTaskId(Integer taskId) {
    List<Step> stepList = stepRepository.findAllByTaskId(taskId);
    stepList.forEach(step -> System.out.println(step.toString()));
    return modelMapper.map(stepList, new TypeToken<List<StepDTO>>() {
    }.getType());
    // return stepRepository.findAllStepByTaskId(taskId).stream()
    // .map(step -> modelMapper.map(step, StepDTO.class))
    // .toList();
  }

  @Override
  public Optional<StepDTO> getStepById(Integer id) {
    return stepRepository.findById(id)
        .map(step -> modelMapper.map(step, StepDTO.class));
  }

  @Override
  @Transactional
  public Optional<StepDTO> updateStep(Integer id, StepDTO stepDTO) {
    return stepRepository.findById(id).map(existingStep -> {
      existingStep.setName(stepDTO.getName());
      existingStep.setDescription(stepDTO.getDescription());
      Step updatedStep = stepRepository.save(existingStep);
      return modelMapper.map(updatedStep, StepDTO.class);
    });
  }

  @Override
  @Transactional
  public Optional<StepDTO> deleteStep(Integer id) {
    return stepRepository.findById(id).map(step -> {
      stepRepository.delete(step);
      return modelMapper.map(step, StepDTO.class);
    });
  }

}