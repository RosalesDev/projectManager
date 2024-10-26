package com.st.project_manager.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.st.project_manager.Dto.UserPersonDTO;
import com.st.project_manager.Entity.UserPerson;

@Mapper
public interface UserPersonMapper {
	UserPersonMapper INSTANCE = Mappers.getMapper(UserPersonMapper.class);
	
	UserPersonDTO toDto(UserPerson userPerson);
	List<UserPersonDTO> toDtoList(List<UserPerson> userPersonList);
	UserPerson toEntity(UserPersonDTO userPersonDto);
}
