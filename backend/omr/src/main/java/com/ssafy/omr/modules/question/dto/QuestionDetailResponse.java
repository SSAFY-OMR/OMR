package com.ssafy.omr.modules.question.dto;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionDetailResponse {
    private InterviewCategory category;
    private String content;
    private Boolean isScraped;
    private String answer;
}
