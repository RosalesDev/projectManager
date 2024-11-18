package com.st.project_manager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st.project_manager.auth.AuthResponse;
import com.st.project_manager.dto.UserPersonDTO;
import com.st.project_manager.service.UserPersonService;

@RestController
@RequestMapping("/api/v1/users")
public class UserPersonController {

	private final UserPersonService userPersonService;

	public UserPersonController(UserPersonService userPersonService) {
		this.userPersonService = userPersonService;
	}

	@PostMapping(value = { "/createUser" }, produces = { "application/json" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AuthResponse> createUserPerson(@RequestBody UserPersonDTO userPersonDTO) {

		AuthResponse createdUserPersonDTO = userPersonService.createUserPerson(userPersonDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(createdUserPersonDTO);
	}

	@GetMapping("/allUsers")
	public List<UserPersonDTO> getAllUsers() {
		return userPersonService.getAllUserPerson();
	}

	@GetMapping("/userById/{id}")
	public ResponseEntity<UserPersonDTO> getUserById(@PathVariable Integer id) {
		Optional<UserPersonDTO> userPersonDto = userPersonService.getUserPersonById(id);
		return ResponseEntity.ok(userPersonDto.get());
	}

	@PutMapping(value = { "/updateUser" }, produces = { "application/json" })
	public ResponseEntity<UserPersonDTO> updateUserPerson(@RequestBody UserPersonDTO userPersonDTO) {

		if (userPersonDTO.getId() == null) {
			return ResponseEntity.badRequest().build();
		}

		UserPersonDTO updatedUserPersonDTO = userPersonService.updateUserPerson(userPersonDTO.getId(), userPersonDTO);

		return ResponseEntity.ok(updatedUserPersonDTO);
	}

	@PutMapping("/deactiveUser/{id}")
	public ResponseEntity<?> deactiveUser(@PathVariable Integer id) {
		userPersonService.deleteUserPerson(id);
		return ResponseEntity.ok().build();
	}

}
