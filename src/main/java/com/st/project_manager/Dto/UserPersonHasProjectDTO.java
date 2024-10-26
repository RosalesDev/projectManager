package com.st.project_manager.Dto;
import lombok.Data;

@Data
public class UserPersonHasProjectDTO extends AuditDTO {

	private Long id;
	
    private Long userPersonId;
	
    private Long projectId;
	
	private String status;
}
