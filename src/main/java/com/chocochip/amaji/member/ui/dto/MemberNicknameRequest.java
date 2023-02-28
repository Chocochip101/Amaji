package com.chocochip.amaji.member.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberNicknameRequest {
    @NotBlank(message = "지원자 이름은 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9가-힣])[a-zA-Z0-9가-힣]{2,20}$",
            message = "닉네임은 2자 이상 20자 이하, 영어 또는 숫자 또는 한글로 구성된 이름이여야 합니다.")
    private String nickname;
}
