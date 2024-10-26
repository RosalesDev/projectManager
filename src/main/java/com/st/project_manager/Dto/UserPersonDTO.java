package com.st.project_manager.Dto;
import lombok.Data;

@Data
public class UserPersonDTO extends AuditDTO{

	public Long id;
	
	public String userName;
	public String firstName;
	public String lastName;
	public String email;
	public String password;
	public String status;

}
