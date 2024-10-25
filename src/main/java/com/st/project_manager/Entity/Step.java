package com.st.project_manager.Entity;

import audit.Audit;
import jakarta.persistence.Entity;
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

 private String description;
 private boolean completed;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "task_id")
 private Task task;
}

