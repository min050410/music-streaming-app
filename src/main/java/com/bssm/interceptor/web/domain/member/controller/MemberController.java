package com.bssm.interceptor.web.domain.member.controller;

import com.bssm.interceptor.web.domain.member.controller.rq.MemberSignUpRq;
import com.bssm.interceptor.web.domain.member.service.MemberService;
import com.bssm.interceptor.web.path.ApiPath;
import com.bssm.interceptor.web.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private final MemberService memberService;

    @PostMapping(ApiPath.MEMBER_SIGNUP)
    public Long signUp(@Validated @RequestBody MemberSignUpRq rq) {
        return this.memberService.signUp(rq);
    }


//    @PostMapping("/login")
//    public String login()

}
