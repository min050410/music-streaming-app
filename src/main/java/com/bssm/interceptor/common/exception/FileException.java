package com.bssm.interceptor.common.exception;

import org.springframework.http.HttpStatus;

public class FileException extends GeneralHttpException {

    public FileException(String message) {
        super(HttpStatus.BAD_REQUEST, message, null);
    }

}
