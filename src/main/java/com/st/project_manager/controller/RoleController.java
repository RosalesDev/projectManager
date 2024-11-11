package com.st.project_manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st.project_manager.dto.RoleDTO;
import com.st.project_manager.service.RoleService;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

  private final RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @PostMapping("/create")
  public Optional<RoleDTO> createRole(@RequestBody RoleDTO roleDto) {
    return roleService.createRole(roleDto);
  }

  @PutMapping("/update/{id}")
  public Optional<RoleDTO> uipdateRole(@PathVariable Integer id, @RequestBody RoleDTO roleDto) {
    return roleService.updateRole(id, roleDto);
  }

  @GetMapping("/by-id/{id}")
  public Optional<RoleDTO> getRoleById(@PathVariable Integer id) {
    return roleService.getRoleById(id);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteRoleById(@PathVariable Integer id) {
    return roleService.deleteRoleById(id);
  }

}
