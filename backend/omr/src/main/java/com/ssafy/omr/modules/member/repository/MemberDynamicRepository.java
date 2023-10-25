package com.ssafy.omr.modules.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.domain.MemberStreak;
import com.ssafy.omr.modules.member.domain.Streak;
import com.ssafy.omr.modules.member.dto.MemberStreakResponse;
import com.ssafy.omr.modules.member.dto.StreakElement;
import com.ssafy.omr.modules.member.mapper.MemberMapper;
import com.ssafy.omr.modules.member.mapper.StreakMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

import static com.ssafy.omr.modules.member.domain.QMember.member;
import static com.ssafy.omr.modules.member.domain.QMemberStreak.memberStreak;
import static com.ssafy.omr.modules.member.domain.QStreak.streak;

@RequiredArgsConstructor
@Repository
public class MemberDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

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

        if (Objects.nonNull(memberStreakInfo)) {
            updateCurrentStreak(memberStreakInfo);
            currentStreak = memberStreakInfo.getCurrentStreak();
            longestStreak = memberStreakInfo.getLongestStreak();
        }
        return MemberMapper.supplyMemberStreakResponseOf(
                streaks,
                currentStreak,
                longestStreak);
    }

    public void updateMemberStreakStatus(Long memberId) {
        LocalDate localDate = LocalDate.now();

        Streak streakInfo = jpaQueryFactory.selectFrom(streak)
                .where(streak.member.id.eq(memberId), streak.streakDate.eq(localDate))
                .fetchOne();

        MemberStreak memberStreakInfo = jpaQueryFactory.selectFrom(memberStreak)
                .where(memberStreak.member.id.eq(memberId))
                .fetchOne();

        if(Objects.isNull(streakInfo)) { //오늘 문제를 푼 적이 없다면 new Streak을 생성해서 저장
            Member memberInfo = jpaQueryFactory.selectFrom(member).where(member.id.eq(memberId)).fetchOne();
            updateMemberStreak(localDate, memberStreakInfo, memberInfo);
        } else { //오늘 문제를 푼 적이 있다면 solvedCount + 1
            streakInfo.increaseSolvedCount();
        }
    }

    private void updateCurrentStreak(MemberStreak memberStreak) {
        LocalDate currentModifiedDate = memberStreak.getModifiedAt().toLocalDate();
        LocalDate localDate = LocalDate.now();
        Period period = Period.between(currentModifiedDate, localDate);

        if (isNotYesterDay(period)) {
            memberStreak.initializeCurrentStreak();
        }
    }

    private boolean isNotYesterDay(Period period) {
        return period.getYears() != 0 || period.getMonths() != 0 || period.getDays() > 1;
    }

    private void updateMemberStreak(LocalDate localDate, MemberStreak memberStreak, Member member) {
        Streak streak;
        if(Objects.nonNull(member)) {
            streak = StreakMapper.supplyStreakEntityOf(member, localDate);
            em.persist(streak);
            if(Objects.nonNull(memberStreak)) {
                updateCurrentStreakAndLongestStreak(memberStreak);
            }
        }
    }

    private void updateCurrentStreakAndLongestStreak(MemberStreak memberStreak) {
        memberStreak.increaseCurrentStreak();
        updateLongestStreak(memberStreak);
    }

    private void updateLongestStreak(MemberStreak memberStreak) {
        if(memberStreak.getCurrentStreak() > memberStreak.getLongestStreak()) {
            memberStreak.updateLongestStreak();
        }
    }
}
