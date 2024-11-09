package com.st.project_manager.dto;

import constant.StepStatus;

public class StepDTO extends AuditDTO {

	private Integer id;

	private String name;

	private String description;

	private boolean isFinalized;

	private Integer taskId;

	private StepStatus status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFinalized() {
		return isFinalized;
	}

	public void setFinalized(boolean isFinalized) {
		this.isFinalized = isFinalized;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StepStatus getStatus() {
		return status;
	}

	public void setStatus(StepStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "StepDTO [id=" + id + ", name=" + name + ", description=" + description + ", isFinalized=" + isFinalized
				+ ", taskId=" + taskId + ", status=" + status + "]";
	}

}
