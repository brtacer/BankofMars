package com.berat.exception;

import lombok.Getter;

@Getter
public class AccountManagerException extends RuntimeException{
    private final ErrorType errorType;

    public AccountManagerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
    public AccountManagerException(ErrorType errorType, String message){
        super(message);
        this.errorType=errorType;
    }
}
