package com.springboot.recipe.exception;

public class UserAlreadyRatedException extends RuntimeException {
    public UserAlreadyRatedException(String message) {
        super(message);
    }
}
