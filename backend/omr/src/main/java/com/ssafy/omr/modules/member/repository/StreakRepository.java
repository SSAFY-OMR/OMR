package com.ssafy.omr.modules.member.repository;

import com.ssafy.omr.modules.member.domain.Streak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreakRepository extends JpaRepository<Streak, Long> {
}
