package com.st.project_manager.dto;

import constant.ProjectStatus;

public class ProjectDTO extends AuditDTO {
	private Integer id;
	private String name;
	private String description;
	private ProjectStatus status;
	private Integer manager_id;

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
		return manager_id;
	}

	public void setManagerId(Integer mangerId) {
		this.manager_id = mangerId;
	}

}
