package com.ssafy.omr.modules.member.mapper;

import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.domain.Streak;

import java.time.LocalDate;

public class StreakMapper {

    public static Streak supplyStreakEntityOf(Member member, LocalDate localDate) {
        return Streak.builder()
                .member(member)
                .streakDate(localDate)
                .build();
    }
}
