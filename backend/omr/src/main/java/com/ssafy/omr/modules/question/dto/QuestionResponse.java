package com.ssafy.omr.modules.question.dto;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionResponse {
    @Schema(description = "문제 id")
    private Long questionId;

    @Schema(description = "카테고리", allowableValues = {"DATA_STRUCTURE", "NETWORK"})
    private InterviewCategory category;

    @Schema(description = "내용")
    private String content;
}
