package com.bssm.interceptor.app.web.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginRq {

    @NotBlank(message = "이메일을 입력해주세요")
    @Schema(description = "이메일")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Schema(description = "패스워드")
    private String password;

}
