package com.st.project_manager.Entity;

import audit.Audit;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Comment extends Audit {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String text;

 @ManyToOne
 @JoinColumn(name = "user_person_id")
 private UserPerson userPerson;

 @ManyToOne
 @JoinColumn(name = "task_id")
 private Task task;
}

