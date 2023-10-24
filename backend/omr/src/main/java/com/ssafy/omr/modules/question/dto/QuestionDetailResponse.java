package com.ssafy.omr.modules.question.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionDetailResponse {
    private String content;
    private Boolean isScraped;
    private String answer;
}
