package com.st.project_manager.controller;

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.st.project_manager.dto.ErrorDTO;

@RestControllerAdvice
public class HandlerExceptionController {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDTO> handleGlobalException(Exception ex, WebRequest request) {
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setDate(new Date());
    errorDTO.setError("Ocurrió un problema.");
    errorDTO.setMessage(ex.getMessage());
    errorDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

    return ResponseEntity.internalServerError().body(errorDTO);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorDTO> duplicateException(Exception ex) {
    ErrorDTO errorDTO = new ErrorDTO();

    errorDTO.setDate(new Date());
    errorDTO.setError("Se está intentano duplicar un dato.");
    errorDTO.setMessage(ex.getMessage());
    errorDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    return ResponseEntity.internalServerError().body(errorDTO);
  }
}
