package com.ssafy.omr.modules.member.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record ChangePasswordRequest(

        @NotEmpty
        @Length(min = 8, max = 16, message = "올바르지 않은 비밀번호 형식입니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$", message = "올바르지 않은 비밀번호 형식입니다.")
        String password
) {
}
