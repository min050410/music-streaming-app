package com.bssm.interceptor.web.exception;

import org.springframework.http.HttpStatus;

public class UserNameNotFoundException extends GeneralHttpException {
    public UserNameNotFoundException() {
        super(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.", null);
    }
}
