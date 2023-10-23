package com.ssafy.omr.modules.member.dto;

public record MemberProfileResponse (
        String loginId,
        String bojId,
        String emoji,
        String nickname
) {}
