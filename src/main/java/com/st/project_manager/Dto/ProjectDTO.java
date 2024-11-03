package com.st.project_manager.Dto;

public class ProjectDTO extends AuditDTO {
	private Integer id;
	private String name;
	private String description;
	private String status;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getOwnerId() {
		return manager_id;
	}
	public void setOwnerId(Integer ownerId) {
		this.manager_id = ownerId;
	}
	
}
