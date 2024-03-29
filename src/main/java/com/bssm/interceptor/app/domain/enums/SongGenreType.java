package com.bssm.interceptor.app.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SongGenreType {
    KPOP("한국 노래"),

    POP("팝송");

    private final String name;

}
