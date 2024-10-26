package com.st.project_manager.service;

import java.util.List;

import com.st.project_manager.Dto.UserPersonDTO;

public interface UserPersonService {
	
	List<UserPersonDTO> getAllUserPerson();
	UserPersonDTO findUserById(Long userId);

}
