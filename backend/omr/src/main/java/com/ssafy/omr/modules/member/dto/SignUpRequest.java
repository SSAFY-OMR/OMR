package com.ssafy.omr.modules.member.dto;

import com.ssafy.omr.modules.member.validator.EmojiCheck;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record SignUpRequest(

        @NotEmpty
        @Pattern(regexp = "^[a-z]+[a-z0-9]{7,15}$", message = "올바르지 않은 아이디 형식입니다.")
        String loginId,

        @NotEmpty
        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$", message = "올바르지 않은 비밀번호 형식입니다.")
        String password,

        @NotEmpty
        @EmojiCheck
        String emoji
) {
}
