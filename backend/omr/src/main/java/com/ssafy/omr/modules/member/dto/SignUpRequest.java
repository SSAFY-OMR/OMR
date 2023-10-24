package com.ssafy.omr.modules.member.dto;

import com.ssafy.omr.modules.member.validator.EmojiCheck;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record SignUpRequest(

        @NotEmpty
        @Length(min = 8, max = 16, message = "올바르지 않은 아이디 형식입니다.")
        @Pattern(regexp = "^[a-z]+[a-z0-9]{8,16}$", message = "올바르지 않은 아이디 형식입니다.1")
        String loginId,

        @NotEmpty
        @Length(min = 8, max = 16, message = "올바르지 않은 비밀번호 형식입니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$", message = "올바르지 않은 비밀번호 형식입니다.")
        String password,

        @NotEmpty
        @EmojiCheck
        String emoji
) {
}
