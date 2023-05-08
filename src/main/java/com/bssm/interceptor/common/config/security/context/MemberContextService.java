package com.bssm.interceptor.common.config.security.context;

import com.bssm.interceptor.app.domain.member.Member;
import com.bssm.interceptor.app.domain.member.MemberRepository;
import com.bssm.interceptor.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberContextService {

    private final MemberRepository memberRepository;

    public MemberContext loadUserByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(UserNotFoundException::new);

        return MemberContext.create(
            member.getEmail(),
            member.getNickname(),
            member.getMemberRoleType());

    }

}
