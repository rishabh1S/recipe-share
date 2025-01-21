package com.springboot.recipe.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(401).body("Authentication is Required");
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<String> handleDuplicateException(DuplicateRecordException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler(NoSearchParamException.class)
    public ResponseEntity<String> handleNoSearchParamException(NoSearchParamException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<String> handleRecipeNotFoundException(RecipeNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(UserAlreadyRatedException.class)
    public ResponseEntity<String> handleUserAlreadyRatedException(UserAlreadyRatedException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
