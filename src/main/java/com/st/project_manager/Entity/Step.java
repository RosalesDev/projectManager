package com.st.project_manager.Entity;

import com.st.project_manager.audit.Audit;

import constant.StepStatus;
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
import lombok.Data;

@Entity
@Data
public class Step extends Audit {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(unique = true)
 private String description;
 
 private boolean isFinalized;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "task_id")
 private Task task;
 
 @Enumerated(EnumType.STRING)
 private StepStatus status;
 
}

