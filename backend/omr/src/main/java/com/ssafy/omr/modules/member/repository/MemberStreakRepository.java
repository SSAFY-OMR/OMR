package com.ssafy.omr.modules.member.repository;

import com.ssafy.omr.modules.member.domain.MemberStreak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStreakRepository extends JpaRepository<MemberStreak, Long> {
}
