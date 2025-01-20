package com.springboot.recipe.exception;

public class DuplicateRecordException extends RuntimeException {
    public DuplicateRecordException(String msg) {
        super(msg);
    }
}
