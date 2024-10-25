package com.st.project_manager.Entity;

import java.util.Set;

import audit.Audit;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class UserPerson extends Audit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String status;
	
	@OneToMany(mappedBy = "userPerson")
	private Set<UserPersonHasProject> projectList;
}
