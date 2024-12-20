package com.st.project_manager.dto;

import constant.CommentStatus;

public class CommentDTO extends AuditDTO {
	private Integer id;
	private String text;
	private Integer userPersonId;
	private Integer taskId;
	private Integer stepId;
	private CommentStatus status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getStepId() {
		return stepId;
	}

	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}

	public CommentStatus getStatus() {
		return status;
	}

	public void setStatus(CommentStatus status) {
		this.status = status;
	}

	public Integer getUserPersonId() {
		return userPersonId;
	}

	public void setUserPersonId(Integer userPersonId) {
		this.userPersonId = userPersonId;
	}

}
