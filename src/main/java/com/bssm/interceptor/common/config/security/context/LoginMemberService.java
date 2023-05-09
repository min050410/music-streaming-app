package com.bssm.interceptor.common.config.security.context;

import com.bssm.interceptor.app.domain.member.Member;
import com.bssm.interceptor.app.domain.member.MemberRepository;
import com.bssm.interceptor.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginMemberService {

    private final MemberRepository memberRepository;

    public LoginMember loadUserByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(UserNotFoundException::new);

        return LoginMember.create(
            member.getId(),
            member.getEmail(),
            member.getNickname(),
            member.getMemberRoleType());

    }

}
