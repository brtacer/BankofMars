package com.berat.exception;

import lombok.Getter;

@Getter
public class CustomerManagerException extends RuntimeException{
    private final ErrorType errorType;

    public CustomerManagerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
    public CustomerManagerException(ErrorType errorType, String message){
        super(message);
        this.errorType=errorType;
    }
}
