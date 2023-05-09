package com.bssm.interceptor.common.config.security.context;

import com.bssm.interceptor.app.domain.enums.MemberRoleType;
import lombok.Getter;

@Getter
public class LoginMember {

    private final Long id;
    private final String email;
    private final String nickname;
    private final MemberRoleType role;

    private LoginMember(Long id, String email, String nickname, MemberRoleType role) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }

    public static LoginMember create(Long id, String email, String nickname, MemberRoleType role) {
        return new LoginMember(id, email, nickname, role);
    }

}