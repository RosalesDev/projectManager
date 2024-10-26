package com.st.project_manager.Dto;

import java.time.LocalDateTime;
import java.util.List;

import com.st.project_manager.Entity.Comment;
import com.st.project_manager.Entity.Project;
import com.st.project_manager.Entity.Step;
import com.st.project_manager.Entity.UserPerson;

import lombok.Data;

@Data
public class TaskDTO extends AuditDTO{

	private Long id;
	
    private LocalDateTime startDate;
	
    private LocalDateTime endDate;

	private String title;
	
	private String description;
	
    private String status;

	private Long projectId;
	
	private Long assignedTo;
	
}
