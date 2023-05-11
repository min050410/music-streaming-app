package com.bssm.interceptor.common.exception;

import org.springframework.http.HttpStatus;

public class NotPossibleToAccessPlaylistException extends GeneralHttpException {

    public NotPossibleToAccessPlaylistException() {
        super(HttpStatus.NOT_FOUND, "재생목록에 접근할 수 있는 권한이 없습니다.", null);
    }
}
