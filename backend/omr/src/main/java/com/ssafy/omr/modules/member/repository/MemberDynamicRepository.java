package com.ssafy.omr.modules.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.omr.modules.member.domain.MemberStreak;
import com.ssafy.omr.modules.member.dto.MemberStreakResponse;
import com.ssafy.omr.modules.member.dto.StreakElement;
import com.ssafy.omr.modules.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.omr.modules.member.domain.QMemberStreak.memberStreak;
import static com.ssafy.omr.modules.member.domain.QStreak.streak;

@RequiredArgsConstructor
@Repository
public class MemberDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public MemberStreakResponse getMemberStreaksInformation(Long memberId, int year, int month) {
        int currentStreak = 0;
        int longestStreak = 0;

        List<StreakElement> streaks = jpaQueryFactory
                .select(Projections.fields(StreakElement.class,
                        streak.streakDate.as("localDate"),
                        streak.solvedCount.as("count")))
                .from(streak)
                .where(streak.member.id.eq(memberId),
                       streak.streakDate.month().eq(month),
                       streak.streakDate.year().eq(year))
                .stream().toList();

        MemberStreak memberStreakInfo = jpaQueryFactory
                .selectFrom(memberStreak)
                .where(memberStreak.id.eq(memberId))
                .fetchOne();

        if(memberStreakInfo != null) {
            currentStreak = memberStreakInfo.getCurrentStreak();
            longestStreak = memberStreakInfo.getLongestStreak();
        }
        return MemberMapper.supplyMemberStreakResponseOf(
                streaks,
                currentStreak,
                longestStreak);
    }
}