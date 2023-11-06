package com.ssafy.omr.modules.question.dto;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionDetailResponse {
    @Schema(description = "카테고리", allowableValues = {"DATA_STRUCTURE", "NETWORK"})
    private InterviewCategory category;

    @Schema(description = "내용")
    private String content;
    
    @Schema(description = "로그인 사용자가 스크랩 했는지 여부")
    private Boolean isScrapped;

    @Schema(description = "나의 답변")
    private String answer;
}
