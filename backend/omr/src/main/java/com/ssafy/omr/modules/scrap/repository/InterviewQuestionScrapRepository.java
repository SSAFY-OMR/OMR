package com.ssafy.omr.modules.scrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.omr.modules.scrap.domain.InterviewQuestionScrap;

@Repository
public interface InterviewQuestionScrapRepository extends JpaRepository<InterviewQuestionScrap, Long> {
	InterviewQuestionScrap findByInterviewQuestionIdAndMemberId(Long questionId, Long memberId);
}
