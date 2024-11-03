package com.st.project_manager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("/userById/{id}")
	public ResponseEntity<UserPersonDTO> getUserById(@PathVariable Integer id) {
		Optional<UserPersonDTO> userPersonDto = userPersonService.getUserPersonById(id);
		return ResponseEntity.ok(userPersonDto.get());
	}

	@GetMapping("/allUsers")
	public List<UserPersonDTO> getAllUsers() {
		return userPersonService.getAllUserPerson();
	}

	@PostMapping(value = { "/createUser" }, produces = { "application/json" })
	public ResponseEntity<UserPersonDTO> createUserPerson(@RequestBody UserPersonDTO userPersonDTO) {

		UserPersonDTO createdUserPersonDTO = userPersonService.createUserPerson(userPersonDTO);

		return new ResponseEntity<UserPersonDTO>(createdUserPersonDTO, HttpStatus.CREATED);
	}

}
