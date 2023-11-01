package com.ssafy.omr.modules.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Map;

public record MemberStreakResponse(

        @Schema(description = "년도 월일에 따른 스트릭 개수", defaultValue = "{\"2023-10-30\": 2, \"2023-11-01\": 3}")
        Map<LocalDate, Integer> streaks,
        int currentStreak,
        int longestStreak
) {
}
