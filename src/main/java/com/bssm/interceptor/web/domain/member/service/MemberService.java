package com.bssm.interceptor.web.domain.member.service;

import com.bssm.interceptor.db.entity.member.Member;
import com.bssm.interceptor.web.domain.member.controller.rq.MemberLoginRq;
import com.bssm.interceptor.web.domain.member.controller.rq.MemberSignUpRq;
import com.bssm.interceptor.web.domain.member.repository.MemberRepository;
import com.bssm.interceptor.web.exception.AlreadyEmailExistException;
import com.bssm.interceptor.web.exception.NoSuchEmailException;
import com.bssm.interceptor.web.exception.PasswordNotMatchException;
import com.bssm.interceptor.web.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public Long signUp(MemberSignUpRq rq) {
        if (memberRepository.findByEmail(rq.getEmail()).isPresent()) {
            throw new AlreadyEmailExistException();
        }
        if (!rq.getPassword().equals(rq.getCheckedPassword())) {
            throw new PasswordNotMatchException();
        }
        return saveMember(rq);
    }

    public Long saveMember(MemberSignUpRq rq) {
        Member member = Member.create(
                rq.getEmail(),
                rq.getNickname(),
                rq.getPassword(),
                rq.getRole());
        memberRepository.save(member);
        member.encodePassword(passwordEncoder);
        return member.getId();
    }

    public String login(MemberLoginRq rq) {
        Member member = memberRepository.findByEmail(rq.getEmail())
                .orElseThrow(NoSuchEmailException::new);
        if (!passwordEncoder.matches(rq.getPassword(), member.getPassword())) {
            throw new PasswordNotMatchException();
        }
        return jwtTokenProvider.createToken(member.getEmail(), member.getNickname());
    }


}
