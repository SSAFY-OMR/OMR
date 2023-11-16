package com.ssafy.omr.modules.member.mapper;

import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.domain.MemberStreak;
import com.ssafy.omr.modules.member.domain.RoleType;
import com.ssafy.omr.modules.member.dto.MemberProfileResponse;
import com.ssafy.omr.modules.member.dto.MemberStreakResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

    public static MemberProfileResponse supplyMemberProfileResponseFrom(Member member, String loginId) {
        return new MemberProfileResponse(
                loginId,
                member.getEmoji(),
                member.getNickname());
    }

    public static Member supplyUserOf(
            String loginId,
            String password,
            String emoji,
            String nickname,
            MemberStreak memberStreak) {
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .emoji(emoji)
                .nickname(nickname)
                .roleType(RoleType.USER)
                .memberStreak(memberStreak)
                .build();
    }

    public static MemberStreakResponse supplyMemberStreakResponseOf(
            Map<LocalDate, Integer> streaks,
            int currentStreak,
            int longestStreak) {
        return new MemberStreakResponse(streaks, currentStreak, longestStreak);
    }

    public static MemberStreak supplyMemberStreakEntity() {
        return MemberStreak.builder().build();
    }
}
