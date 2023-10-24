package com.ssafy.omr.modules.scrap.repository;

import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.omr.modules.scrap.domain.InterviewQuestionScrap;

@Repository
public interface InterviewQuestionScrapRepository extends JpaRepository<InterviewQuestionScrap, Long> {
	InterviewQuestionScrap findByInterviewQuestionIdAndMemberId(Long questionId, Long memberId);

	Boolean existsByInterviewQuestionAndMember(InterviewQuestion interviewQuestion, Member member);
}
