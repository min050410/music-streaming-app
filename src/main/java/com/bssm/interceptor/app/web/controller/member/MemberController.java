package com.bssm.interceptor.app.web.controller.member;

import com.bssm.interceptor.app.web.dto.member.MemberLoginRq;
import com.bssm.interceptor.app.web.dto.member.MemberSignUpRq;
import com.bssm.interceptor.app.domain.member.MemberService;
import com.bssm.interceptor.common.config.security.context.MemberContext;
import com.bssm.interceptor.app.web.path.ApiPath;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "멤버")
@RequiredArgsConstructor
@RestController
public class MemberController {

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
