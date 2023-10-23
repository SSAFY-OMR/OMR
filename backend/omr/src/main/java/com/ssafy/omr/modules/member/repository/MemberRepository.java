package com.ssafy.omr.modules.member.repository;

import com.ssafy.omr.modules.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginIdAndPassword(String loginId, String password);
}
