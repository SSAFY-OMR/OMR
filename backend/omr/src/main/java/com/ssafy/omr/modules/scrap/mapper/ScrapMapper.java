package com.ssafy.omr.modules.scrap.mapper;

import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.scrap.domain.InterviewQuestionScrap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScrapMapper {

	public static InterviewQuestionScrap supplyInterviewQuestionScrapOf(final Member member, final InterviewQuestion interviewQuestion) {
		return InterviewQuestionScrap.builder()
			.member(member)
			.interviewQuestion(interviewQuestion)
			.build();
	}
}
