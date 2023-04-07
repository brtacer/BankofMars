package com.berat.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    UNEXPECTED(1000,"Unexpected error!", INTERNAL_SERVER_ERROR),
    INVALID_PARAMETER(1001,"Invalid parameter entered",BAD_REQUEST),
    NOT_DECODED(1002,"Token could not be decoded",BAD_REQUEST),
    INVALID_TOKEN(1003,"Token not valid",FORBIDDEN),
    HTTP_MESSAGE_NOT_READABLE(1004,"Http message not readable!",BAD_REQUEST),
    INVALID_FORMAT(1005,"Format not valid!",BAD_REQUEST),
    MISSING_PATH_VARIABLE(1006,"Path variable missing!",BAD_REQUEST),
    METHOD_ARGUMENT_TYPE_MISMATCH(1007,"Method argument type mismatch!",BAD_REQUEST),
    TOKEN_NOT_CREATED(1008,"Token not created!",BAD_REQUEST),

    INSUFFICIENT_LIMIT(1400,"Insufficient card limit!",BAD_REQUEST),
    PAYMENT_FAILED(1401,"Debt payment failed!",BAD_REQUEST),
    ACCOUNT_NOT_FOUND(1300,"Account not found!",NOT_FOUND),
    CARD_NOT_FOUND(1402,"Card not found!",NOT_FOUND),
    CARD_NOT_ACTIVE(1403,"Card not active!",NOT_FOUND),



    ;
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
