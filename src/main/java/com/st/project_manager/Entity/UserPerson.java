package com.st.project_manager.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import java.util.Set;

import com.st.project_manager.audit.Audit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;

@Entity
public class UserPerson extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String userName;
	private String firstName;
	private String lastName;

	@Column(unique = true)
	private String email;
	@JsonIgnore
	private String password;
	@Column(columnDefinition = "VARCHAR(20)")
	private String status;
	//
	// @OneToMany(mappedBy = "userPerson")
	// private Set<ProjectHasUserPerson> projectList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// public Set<ProjectHasUserPerson> getProjectList() {
	// return projectList;
	// }
	//
	// public void setProjectList(Set<ProjectHasUserPerson> projectList) {
	// this.projectList = projectList;
	// }
}
