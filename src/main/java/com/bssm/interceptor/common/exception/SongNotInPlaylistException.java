package com.bssm.interceptor.common.exception;

import org.springframework.http.HttpStatus;

public class SongNotInPlaylistException extends GeneralHttpException {

    public SongNotInPlaylistException() {
        super(HttpStatus.BAD_REQUEST, "곡이 플레이리스트에 포함되어 있지 않습니다.", null);
    }

}
