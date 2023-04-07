package com.berat.exception;

import lombok.Getter;

@Getter
public class TransactionManagerException extends RuntimeException{
    private final ErrorType errorType;

    public TransactionManagerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
    public TransactionManagerException(ErrorType errorType, String message){
        super(message);
        this.errorType=errorType;
    }
}
