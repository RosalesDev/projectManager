package com.st.project_manager.exception;

public class ProjectAlreadyHasManagerException extends RuntimeException {
  public ProjectAlreadyHasManagerException(String message) {
    super(message);
  }
}
