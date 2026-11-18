package com.sixflags.parkmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity> handleBusinessException(BusinessException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("codigo", ex.getCodigo());
        error.put("mensaje", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("codigo", "SOLICITUD_INVALIDA");
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        error.put("mensaje", message != null ? message : "Solicitud sin campos obligatorios o mal formada");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}