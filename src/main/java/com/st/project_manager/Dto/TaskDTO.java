package com.st.project_manager.dto;

import java.time.LocalDateTime;

public class TaskDTO extends AuditDTO {

	private Integer id;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private String title;

	private String description;

	private String status;

	private Integer projectId;

	private Integer assignedTo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Integer assignedTo) {
		this.assignedTo = assignedTo;
	}

}
