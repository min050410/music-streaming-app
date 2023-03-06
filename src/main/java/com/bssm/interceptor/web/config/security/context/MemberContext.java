package com.bssm.interceptor.web.config.security.context;

import com.bssm.interceptor.db.enums.MemberRoleType;
import lombok.Getter;

@Getter
public class MemberContext {
    private final String email;
    private final String nickname;
    private final MemberRoleType role;

    private MemberContext(String email, String nickname, MemberRoleType role) {
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }

    public static MemberContext create(String email, String nickname, MemberRoleType role) {
        return new MemberContext(email, nickname, role);
    }

}