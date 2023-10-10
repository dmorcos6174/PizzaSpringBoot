package com.example.pizzaspringboot.exception_handler;

import com.example.pizzaspringboot.exception.PizzaAlreadyExistsException;
import com.example.pizzaspringboot.exception.PizzaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class PizzaExceptionHandler {

    @ExceptionHandler(PizzaAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlePizzaAlreadyExistsException(PizzaAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(PizzaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePizzaNotFoundException(PizzaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
    }

}
