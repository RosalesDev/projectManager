package com.st.project_manager.dto;

public class ProjectHasUserPersonDTO extends AuditDTO {

	private Integer id;

	private Integer userPersonId;

	private Integer projectId;

	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserPersonId() {
		return userPersonId;
	}

	public void setUserPersonId(Integer userPersonId) {
		this.userPersonId = userPersonId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
