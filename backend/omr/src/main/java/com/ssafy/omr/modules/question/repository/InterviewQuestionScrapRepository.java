package com.ssafy.omr.modules.question.repository;

import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.domain.InterviewQuestionScrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterviewQuestionScrapRepository extends JpaRepository<InterviewQuestionScrap, Long> {
     Boolean existsByInterviewQuestionAndMember(InterviewQuestion interviewQuestion, Member member);
}
