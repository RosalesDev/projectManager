package com.st.project_manager.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import com.st.project_manager.dto.TaskDTO;
import com.st.project_manager.entity.Task;

@Component
public class TaskMapper {

  private final ModelMapper modelMapper;

  public TaskMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
    configureMappings();
  }

  private void configureMappings() {
    modelMapper.typeMap(Task.class, TaskDTO.class)
        .addMappings(mapper -> mapper.map(src -> src.getUserPerson().getId(),
            TaskDTO::setUserPersonId));
  }

  public TaskDTO toDTO(Task task) {
    return modelMapper.map(task, TaskDTO.class);
  }

  public Task toEntity(TaskDTO taskDto) {
    return modelMapper.map(taskDto, Task.class);
  }

  public List<TaskDTO> toDTOList(List<Task> taskList) {
    return modelMapper.map(taskList, new TypeToken<List<TaskDTO>>() {
    }.getType());
  }
}
