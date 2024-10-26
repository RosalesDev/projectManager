package com.st.project_manager.Entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import com.st.project_manager.audit.Audit;

import constant.TaskStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Data
public class Task extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "start_date", updatable = false, nullable = false)
    private LocalDateTime startDate;
	
	@Column(name = "end_date", updatable = false, nullable = false)
    private LocalDateTime endDate;

	@Column(unique = true)
	private String title;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
    private TaskStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
	private List<Step> steps;

	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assigned_to")
	private UserPerson userPerson;
}
