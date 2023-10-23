package com.ssafy.omr.modules.auth.dto;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record LoginRequest(
        @Length(min = 2, max = 10, message = "올바르지 않은 아이디 형식입니다.")
        @Pattern(regexp = "/^[a-z]+[a-z0-9]{5,19}$/", message = "올바르지 않은 아이디 형식입니다.")
        String loginId,

        @Length(min = 8, max = 16, message = "올바르지 않은 비밀번호 형식입니다.")
        @Pattern(regexp = "/^(?=.\\d)(?=.[a-zA-Z])[0-9a-zA-Z]{8,16}$/", message = "올바르지 않은 비밀번호 형식입니다.")
        String password
) {
}
