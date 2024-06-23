package com.naruto.apinaruto.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message, Throwable error) {
        super(message, error);
    }
}
