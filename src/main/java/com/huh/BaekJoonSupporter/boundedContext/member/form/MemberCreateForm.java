package com.huh.BaekJoonSupporter.boundedContext.member.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateForm {

    @NotEmpty(message = "ID를 입력해주세요.")
    private String username;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password1;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password2;

    @NotEmpty(message = "토큰을 입력해주세요.")
    private String token;

}
