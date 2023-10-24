package com.ssafy.omr.modules.answer.repository;

import com.ssafy.omr.modules.answer.domain.Answer;
import com.ssafy.omr.modules.answer.domain.AnswerLike;
import com.ssafy.omr.modules.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerLikeRepository extends JpaRepository<AnswerLike, Long> {

    AnswerLike findByMemberAndAnswer(Member member, Answer answer);

}
