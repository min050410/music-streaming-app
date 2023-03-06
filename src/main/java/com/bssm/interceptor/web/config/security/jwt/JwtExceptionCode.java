package com.bssm.interceptor.web.config.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtExceptionCode {

    EXPIRED("ExpiredJwtException", "유효기간이 만료된 토큰입니다."),
    UNSUPPORTED("UnsupportedJwtException", "지원하지 않는 형식의 토큰입니다."),
    MALFORMED("MalformedJwtException", "올바른 구성의 토큰이 아닙니다."),
    INVALID_SIGNATURE("InvalidJwtSignatureException", "서명을 확인할 수 없는 토큰입니다."),
    INVALID("InvalidJwtException", "유효하지 않은 토큰입니다.");
    private final String code;
    private final String message;
}