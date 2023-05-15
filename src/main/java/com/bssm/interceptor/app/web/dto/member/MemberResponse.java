package com.bssm.interceptor.app.web.dto.member;

import com.bssm.interceptor.app.domain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberResponse {

    private long id;

    private String nickname;

    public static MemberResponse of(Member member) {
        return new MemberResponse(
            member.getId(),
            member.getNickname()
        );
    }

}
