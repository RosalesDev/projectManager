package com.st.project_manager.Dto;
import lombok.Data;

@Data
public class ProjectDTO extends AuditDTO {

	private Long id;
	private String name;
	private String description;
	private String status;
	private Long ownerId;
}
