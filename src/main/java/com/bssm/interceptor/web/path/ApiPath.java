package com.bssm.interceptor.web.path;

public class ApiPath {

    // 에러 핸들러
    public static final String ERROR_AUTH = "/api/error";

    // 멤버
    public static final String MEMBER = "/api/member";
    public static final String MEMBER_SIGNUP = "/api/member/signup";
    public static final String MEMBER_LOGIN = "/api/member/login";

    // 곡
    public static final String SONG = "/api/song";
    public static final String SONG_BY_ID = "/api/song/{id}";
}
