package com.spring.assessment.exception;

import com.spring.assessment.dto.ErrorDTO;
import com.spring.assessment.exception.custom.DataNotFoundException;
import com.spring.assessment.exception.custom.UnprocessableEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleDataNotFoundException(DataNotFoundException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UnprocessableEntityException.class})
    public ResponseEntity<ErrorDTO> handle(UnprocessableEntityException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
