package com.bssm.interceptor.common.exception;

import org.springframework.http.HttpStatus;

public class PlaylistNotFoundException extends GeneralHttpException {

    public PlaylistNotFoundException() {
        super(HttpStatus.NOT_FOUND, "플레이 리스트를 찾을 수 없습니다.", null);
    }
}
