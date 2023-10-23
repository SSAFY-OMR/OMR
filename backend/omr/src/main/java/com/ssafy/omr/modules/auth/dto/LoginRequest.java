package com.ssafy.omr.modules.auth.dto;

public record LoginRequest(
        String id,
        String password
) {
}
