package com.alexlo.msvc_user.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("type", "https://api.tuservicio.com/errors/data-integrity");
        body.put("title", "Violación de integridad de datos");
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("detail", e.getMostSpecificCause().getMessage());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("type", "https://api.tuservicio.com/errors/illegal-argument");
        body.put("title", "Parámetro inválido");
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("detail", e.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOtherExceptions(Exception e) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("type", "https://api.tuservicio.com/errors/internal-server-error");
        body.put("title", "Error interno del servidor");
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("detail", e.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("type", "https://api.tuservicio.com/errors/not-found");
        body.put("title", "Recurso no encontrado");
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("detail", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        });

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("type", "https://api.tuservicio.com/errors/validation-error");
        body.put("title", "Solicitud inválida");
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("detail", "Uno o más campos no cumplen con los requisitos");
        body.put("timestamp", LocalDateTime.now());
        body.put("errors", fieldErrors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
