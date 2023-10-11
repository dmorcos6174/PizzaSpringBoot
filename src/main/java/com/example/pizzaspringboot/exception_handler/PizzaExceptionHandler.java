package com.example.pizzaspringboot.exception_handler;

import com.example.pizzaspringboot.exception.PizzaAlreadyExistsException;
import com.example.pizzaspringboot.exception.PizzaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PizzaExceptionHandler {

    @ExceptionHandler(PizzaAlreadyExistsException.class)
    public ResponseEntity<?> handlePizzaAlreadyExistsException(PizzaAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    //implement using @ResponseStatus and return map if i want json as a reply

    @ExceptionHandler(PizzaNotFoundException.class)
    public ResponseEntity<?> handlePizzaNotFoundException(PizzaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}