package com.bssm.interceptor.web.config.security.context;

import com.bssm.interceptor.db.entity.member.Member;
import com.bssm.interceptor.web.domain.member.repository.MemberRepository;
import com.bssm.interceptor.web.exception.UserNotFoundException;
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
