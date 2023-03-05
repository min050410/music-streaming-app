package com.bssm.interceptor.web.domain.member.service;

import com.bssm.interceptor.db.entity.member.Member;
import com.bssm.interceptor.web.domain.member.controller.rq.MemberSignUpRq;
import com.bssm.interceptor.web.domain.member.repository.MemberRepository;
import com.bssm.interceptor.web.exception.AlreadyEmailExistException;
import com.bssm.interceptor.web.exception.PasswordNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long signUp (MemberSignUpRq rq) {

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

}
