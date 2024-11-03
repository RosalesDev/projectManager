package com.st.project_manager.Entity;

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

@Entity
public class Step extends Audit {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;

 @Column(unique = true)
 private String description;
 
 private boolean isFinalized;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "task_id")
 private Task task;
 
 @Enumerated(EnumType.STRING)
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
 
 
 
}

