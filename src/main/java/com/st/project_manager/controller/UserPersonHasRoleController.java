package com.st.project_manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st.project_manager.dto.UserPersonHasRoleDTO;
import com.st.project_manager.service.UserPersonHasRoleService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/user-role")
public class UserPersonHasRoleController {

  private final UserPersonHasRoleService userPersonHasRoleService;

  public UserPersonHasRoleController(UserPersonHasRoleService userPersonHasRoleService) {
    this.userPersonHasRoleService = userPersonHasRoleService;
  }

  @PostMapping("/create")
  public ResponseEntity<UserPersonHasRoleDTO> createUserPersonRole(@RequestBody UserPersonHasRoleDTO uphrDto) {
    return ResponseEntity.ok(userPersonHasRoleService.createUserRole(uphrDto).get());
  }

  @PostMapping("/update/{id}")
  public ResponseEntity<UserPersonHasRoleDTO> updatePersonRole(@PathVariable Integer id,
      @RequestBody UserPersonHasRoleDTO uphrDto) {
    return ResponseEntity.ok(userPersonHasRoleService.updateUserRole(id, uphrDto).get());
  }

  @GetMapping("/by-person/{id}")
  public ResponseEntity<UserPersonHasRoleDTO> getUserPersonRolByPerson(@PathVariable Integer id) {
    return ResponseEntity.ok(userPersonHasRoleService.getUserRoleByUserPersonId(id).get());
  }

}
