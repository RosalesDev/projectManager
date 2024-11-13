package com.st.project_manager.dto;

import constant.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProjectDTO extends AuditDTO {
	private Integer id;
	@NotBlank(message = "El nombre del proyecto es requerido.")
	@NotEmpty(message = "El nombre no puede estar vacío.")
	@Size(min = 3, max = 140, message = "El nombre debe tener entre 3 y 140 caracteres.")
	private String name;

	@NotBlank(message = "La descripción es requerida.")
	@NotEmpty(message = "La descripción no puede estar vacía.")
	@Size(min = 3, max = 140, message = "La descripción debe tener entre 3 y 140 caracteres.")
	private String description;
	private ProjectStatus status;
	private Integer managerId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "ProjectDTO [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", managerId=" + managerId + "]";
	}

}
