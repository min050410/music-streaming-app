package com.bssm.interceptor.common.exception;

import org.springframework.http.HttpStatus;

public class SongNotFoundException extends GeneralHttpException {

    public SongNotFoundException() {
        super(HttpStatus.NOT_FOUND, "곡을 찾을 수 없습니다.", null);
    }
}
