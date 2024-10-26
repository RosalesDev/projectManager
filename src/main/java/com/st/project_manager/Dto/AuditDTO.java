package com.st.project_manager.Dto;

import java.time.LocalDateTime;

public abstract class AuditDTO{

	public LocalDateTime dateCreated;

	public LocalDateTime dateModified;

	public Long createdBy;

	public Long modifiedBy;

}
