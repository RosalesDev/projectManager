package com.st.project_manager.Dto;

import lombok.Data;

@Data
public class StepDTO extends AuditDTO {

	private Long id;

	 private String description;
	 
	 private boolean isFinalized;

	 private Long taskId;
	 
	 private String status;
}
