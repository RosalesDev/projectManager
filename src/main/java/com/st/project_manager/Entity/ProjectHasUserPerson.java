package com.st.project_manager.entity;

import com.st.project_manager.audit.Audit;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProjectHasUserPerson extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_person_id")
	private UserPerson userPerson;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	private Boolean enable;

	public ProjectHasUserPerson(Integer projectId, Integer userPersonId) {
		this.project.setId(projectId);
		this.userPerson.setId(userPersonId);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserPerson getUserPerson() {
		return userPerson;
	}

	public Integer getUserPersonId() {
		return this.project.getId();
	}

	public void setUserPerson(UserPerson userPerson) {
		this.userPerson = userPerson;
	}

	public void setUserPersonId(Integer id) {
		this.userPerson.setId(id);
	}

	public Project getProject() {
		return project;
	}

	public Integer getProjectId() {
		return this.project.getId();
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setProjectId(Integer id) {
		this.project.setId(id);
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
