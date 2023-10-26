package com.ssafy.omr.modules.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.omr.modules.member.domain.Streak;
import com.ssafy.omr.modules.member.dto.StreakElement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import static com.ssafy.omr.modules.member.domain.QStreak.streak;

@RequiredArgsConstructor
@Repository
public class MemberDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;


    public List<StreakElement> getStreaksByMemberId(Long memberId, int year, int month) {
        return jpaQueryFactory
                .select(Projections.fields(StreakElement.class,
                        streak.streakDate.as("localDate"),
                        streak.solvedCount.as("count")))
                .from(streak)
                .where(streak.member.id.eq(memberId),
                        streak.streakDate.month().eq(month),
                        streak.streakDate.year().eq(year))
                .stream().toList();
    }

    public Streak getTodayMemberStreak(Long memberId, LocalDate localDate) {
        return jpaQueryFactory.selectFrom(streak)
                .where(streak.member.id.eq(memberId), streak.streakDate.eq(localDate))
                .fetchOne();
    }
}
