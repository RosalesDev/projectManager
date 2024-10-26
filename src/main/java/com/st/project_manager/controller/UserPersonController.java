package com.st.project_manager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st.project_manager.Dto.UserPersonDTO;
import com.st.project_manager.service.UserPersonService;

@RestController
@RequestMapping("/api/v1/users")
public class UserPersonController {
	
	private final UserPersonService userPersonService;

	public UserPersonController(UserPersonService userPersonService) {
		this.userPersonService = userPersonService;
	}
	
	@GetMapping("/all")
	public List<UserPersonDTO> getAllUsers(){
		return userPersonService.getAllUserPerson();
	}
	
	
	
}
