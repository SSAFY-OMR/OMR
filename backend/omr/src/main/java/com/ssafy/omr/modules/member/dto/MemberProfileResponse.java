package com.ssafy.omr.modules.member.dto;

public record MemberProfileResponse (
        String loginId,
        String emoji,
        String nickname
) {}
