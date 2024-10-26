package com.st.project_manager.Dto;
import lombok.Data;

@Data
public class CommentDTO extends AuditDTO {
	private Long id;
	 private String text;
	 private Long personId;
	 private Long taskId;
	 private Long stepId;
}
