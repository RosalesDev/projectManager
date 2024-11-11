package com.st.project_manager.exception;

public class InvalidIdException extends RuntimeException {
  public InvalidIdException() {
    super("El ID no es válido.");
  }
}
