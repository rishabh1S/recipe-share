package com.springboot.recipe.exception;

public class NoSearchParamException extends RuntimeException {
    public NoSearchParamException(String msg) {
        super(msg);
    }
}
