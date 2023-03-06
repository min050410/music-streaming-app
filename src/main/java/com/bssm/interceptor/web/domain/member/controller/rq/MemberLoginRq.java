package com.bssm.interceptor.web.domain.member.controller.rq;

import com.bssm.interceptor.db.enums.MemberRoleType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginRq {

    @NotBlank(message = "이메일을 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

}
