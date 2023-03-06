package com.bssm.interceptor.web.exception;

import org.springframework.http.HttpStatus;
public class NoSuchEmailException extends GeneralHttpException {
    public NoSuchEmailException() {
        super(HttpStatus.BAD_REQUEST, "가입되지 않은 이메일입니다.", null);
    }

}
