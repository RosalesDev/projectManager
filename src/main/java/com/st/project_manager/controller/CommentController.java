package com.st.project_manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st.project_manager.dto.CommentDTO;
import com.st.project_manager.service.CommentService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @PostMapping("/create")
  public Optional<CommentDTO> createComment(@RequestBody CommentDTO commentDto) {
    return commentService.createComment(commentDto);
  }

  @PutMapping("update/{id}")
  public Optional<CommentDTO> updateComment(@PathVariable Integer id, @RequestBody CommentDTO commentDto) {

    return commentService.updateComment(id, commentDto);
  }

  @GetMapping("/by-user/{userPersonId}")
  public List<CommentDTO> getCommentByUserPersonId(@PathVariable Integer userPersonId) {
    return commentService.getCommentByPersonId(userPersonId);
  }

  @GetMapping("/by-task/{taskId}")
  public List<CommentDTO> getCommentByTaskId(@PathVariable Integer taskId) {
    return commentService.getCommentByTaskId(taskId);
  }

  @GetMapping("/by-step/{stepId}")
  public List<CommentDTO> getCommentByStepId(@PathVariable Integer stepId) {
    return commentService.getCommentByStepId(stepId);
  }

}
