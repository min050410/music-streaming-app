package com.bssm.interceptor.app.web.path;

public class ApiPath {

    // 에러 핸들러
    public static final String ERROR_AUTH = "/api/error";

    // 멤버
    public static final String MEMBER = "/api/member";
    public static final String MEMBER_SIGNUP = "/api/member/signup";
    public static final String MEMBER_LOGIN = "/api/member/login";

    // 곡
    public static final String SONG = "/api/song";
    public static final String SONG_CREATE = "/api/song";
    public static final String SONG_BY_ID = "/api/song/{id}";

    // 플레이리스트
    public static final String PLAYLIST = "/api/playlist";
    public static final String PLAYLIST_CREATE = "/api/playlist";
    public static final String PLAYLIST_SONG_ADD = "/api/playlist/song/add";

    public static final String PLAYLIST_SONG_DELETE = "/api/playlist/song/delete";
    public static final String PLAYLIST_FIND = "/api/playlist/find/{id}";
    public static final String PLAYLIST_UPDATE = "/api/playlist/update/{id}";
    public static final String PLAYLIST_DELETE = "/api/playlist/delete/{id}";

}
