package com.bssm.interceptor.web.exception;

import org.springframework.http.HttpStatus;

public class AlreadyEmailExistException extends GeneralHttpException {
    public AlreadyEmailExistException() {
        super(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다.", null);
    }
}
