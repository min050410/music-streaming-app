package com.bssm.interceptor.web.exception;

import org.springframework.http.HttpStatus;

public class PasswordNotMatchException extends GeneralHttpException {
    public PasswordNotMatchException() {
        super(HttpStatus.UNAUTHORIZED, "패스워드가 일치하지 않습니다.", null);
    }
}
