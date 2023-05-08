package com.bssm.interceptor.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GeneralHttpException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;
    private final Throwable cause;

    public GeneralHttpException(final HttpStatus httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.message = message;
        this.cause = cause;
    }

}
