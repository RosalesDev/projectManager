package com.st.project_manager.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import com.st.project_manager.dto.RoleDTO;
import com.st.project_manager.entity.Role;

@Component
public class RoleMapper {

  private final ModelMapper modelMapper;

  public RoleMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public RoleDTO toDTO(Role role) {
    return modelMapper.map(role, RoleDTO.class);
  }

  public Role toEntity(RoleDTO roleDto) {
    return modelMapper.map(roleDto, Role.class);
  }

  public List<RoleDTO> toDTOList(List<Role> roleList) {
    return modelMapper.map(roleList, new TypeToken<List<RoleDTO>>() {
    }.getType());
  }

}
