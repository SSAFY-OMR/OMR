package com.ssafy.omr.modules.member.dto;

import java.time.LocalDate;
import java.util.Map;

public record MemberStreakResponse(
        Map<LocalDate, Integer> streaks,
        int currentStreak,
        int longestStreak
) {
}
