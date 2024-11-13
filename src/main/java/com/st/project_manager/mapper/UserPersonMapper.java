package com.st.project_manager.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import com.st.project_manager.dto.UserPersonDTO;
import com.st.project_manager.entity.UserPerson;

@Component
public class UserPersonMapper {

  private final ModelMapper modelMapper;

  public UserPersonMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public UserPersonDTO toDTO(UserPerson userPerson) {
    return modelMapper.map(userPerson, UserPersonDTO.class);
  }

  public UserPerson toEntity(UserPersonDTO userPersonDto) {
    return modelMapper.map(userPersonDto, UserPerson.class);
  }

  public List<UserPersonDTO> toDtoList(List<UserPerson> userPersonList) {
    return modelMapper.map(userPersonList, new TypeToken<List<UserPersonDTO>>() {
    }.getType());
  }
}
