package com.ssafy.omr.modules.auth.dto;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
