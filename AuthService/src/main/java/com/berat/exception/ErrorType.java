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

    PASSWORD_AND_REPASSWORD_NOT_MATCH(1100,"Password and rePassword not match",BAD_REQUEST),
    INCORRECT_USERNAME_OR_PASSWORD(1101,"Username or password incorrect!",BAD_REQUEST),
    USER_NOT_FOUND(1102,"User not found!",NOT_FOUND),
    INVALID_ACTIVATION_CODE(1103,"Invalid activation code entered!",BAD_REQUEST),
    INVALID_PASSWORD(1104,"Invalid password entered!",BAD_REQUEST),
    USERNAME_ALREADY_EXIST(1105,"Username already exist!",BAD_REQUEST),
    EMAIL_ALREADY_EXIST(1106,"Email already exist!",BAD_REQUEST),
    PHONE_ALREADY_EXIST(1107,"Phone already exist!",BAD_REQUEST),

    ;
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
