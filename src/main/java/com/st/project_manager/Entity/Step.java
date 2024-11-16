package com.st.project_manager.entity;

import com.st.project_manager.audit.Audit;

import constant.StepStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Step extends Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	private String description;

	@Column(columnDefinition = "BIT DEFAULT 1")
	private boolean isFinalized;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id")
	private Task task;

	@Enumerated(EnumType.STRING)
	private StepStatus status;

	@PrePersist
	public void prePersist() {
		if (status == null) {
			status = StepStatus.PENDING;
		}
	}

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

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
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

	public void setName(String name) {
		this.name = name;
	}

}
