package com.bssm.interceptor.common.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends GeneralHttpException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.", null);
    }
}
