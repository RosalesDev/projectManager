package com.st.project_manager.exception;

public class TaskWithoutStepsCompletedException extends RuntimeException {
  public TaskWithoutStepsCompletedException() {
    super("No se puede completar una tarea con pasos sin finalizar.");
  }
}
