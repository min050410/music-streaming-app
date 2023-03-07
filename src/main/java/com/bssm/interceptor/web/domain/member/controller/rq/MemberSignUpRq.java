package com.bssm.interceptor.web.domain.member.controller.rq;

import com.bssm.interceptor.db.enums.MemberRoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MemberSignUpRq {

    @NotBlank(message = "이메일을 입력해주세요")
    @Schema(description = "이메일")
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요")
    @Schema(description = "닉네임")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
            message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    @Schema(description = "패스워드")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요")
    @Schema(description = "비밀번호 확인")
    private String checkedPassword;

    private MemberRoleType role;

}
