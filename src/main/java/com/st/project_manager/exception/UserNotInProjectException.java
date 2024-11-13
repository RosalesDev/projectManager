package com.st.project_manager.exception;

public class UserNotInProjectException extends RuntimeException {
  public UserNotInProjectException() {
    super("No se puede asignar la tarea porque el usuario no está en el proyecto.");
  }
}
