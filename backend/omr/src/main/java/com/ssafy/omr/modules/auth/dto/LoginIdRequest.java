package com.ssafy.omr.modules.auth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record LoginIdRequest(
        @NotNull
        @Length(min = 8, max = 16, message = "올바르지 않은 아이디 형식입니다.")
        @Pattern(regexp = "^[a-z]+[a-z0-9]{8,16}$", message = "올바르지 않은 아이디 형식입니다.")
        String loginId
) {
}
