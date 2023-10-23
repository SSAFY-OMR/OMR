package com.ssafy.omr.modules.auth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record LoginRequest(

        @NotNull
        @Length(min = 8, max = 16, message = "올바르지 않은 아이디 형식입니다.")
        @Pattern(regexp = "^[a-z]+[a-z0-9]{8,16}$", message = "올바르지 않은 아이디 형식입니다.1")
        String loginId,

        @NotNull
        @Length(min = 8, max = 16, message = "올바르지 않은 비밀번호 형식입니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$", message = "올바르지 않은 비밀번호 형식입니다.1")
        String password
) {
}
