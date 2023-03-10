package com.bssm.interceptor.web.domain.member.controller;

import com.bssm.interceptor.web.config.security.context.MemberContext;
import com.bssm.interceptor.web.domain.member.controller.rq.MemberLoginRq;
import com.bssm.interceptor.web.domain.member.controller.rq.MemberSignUpRq;
import com.bssm.interceptor.web.domain.member.service.MemberService;
import com.bssm.interceptor.web.path.ApiPath;
import com.bssm.interceptor.web.config.security.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name= "멤버")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private final MemberService memberService;

    @Operation(summary = "회원가입")
    @PostMapping(ApiPath.MEMBER_SIGNUP)
    public Long signUp(@Validated @RequestBody MemberSignUpRq rq) {
        return this.memberService.signUp(rq);
    }

    @Operation(summary = "로그인")
    @PostMapping(ApiPath.MEMBER_LOGIN)
    public String login(@Validated @RequestBody MemberLoginRq rq) {
        return this.memberService.login(rq);
    }

    @Operation(summary = "내 정보 불러오기")
    @GetMapping(ApiPath.MEMBER)
    public MemberContext FindMemberSelf(@AuthenticationPrincipal MemberContext memberContext) {
        return memberContext;
    }


}
