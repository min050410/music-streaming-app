package com.bssm.interceptor.common.exception;

import org.springframework.http.HttpStatus;

public class PlaylistSongDuplicateException extends GeneralHttpException {

    public PlaylistSongDuplicateException() {
        super(HttpStatus.BAD_REQUEST, "플레이리스트의 곡이 중복될 수 없습니다.", null);
    }
}
