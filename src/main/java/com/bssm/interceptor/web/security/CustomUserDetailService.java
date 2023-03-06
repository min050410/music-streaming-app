package com.bssm.interceptor.web.security;

import com.bssm.interceptor.db.entity.member.Member;
import com.bssm.interceptor.web.domain.member.repository.MemberRepository;
import com.bssm.interceptor.web.exception.UserNameNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(UserNameNotFoundException::new);

        // TODO :: 어떻게 리턴되는지 확인
        return CustomUserDetails.create(
                member.getEmail(),
                member.getPassword());
    }

}
