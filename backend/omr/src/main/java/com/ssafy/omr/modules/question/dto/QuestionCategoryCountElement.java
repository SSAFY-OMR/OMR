package com.ssafy.omr.modules.question.dto;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionCategoryCountElement {
	private InterviewCategory interviewCategory;
	private Long count;

	public QuestionCategoryCountElement(InterviewCategory interviewCategory, Long count) {
		this.interviewCategory = interviewCategory;
		this.count = count;
	}
}
