package com.ssafy.omr.modules.auth.dto;

public record AuthInfo (
        Long id,
        String role,
        String nickname
) {
}
