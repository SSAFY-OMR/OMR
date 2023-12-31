package com.ssafy.omr.modules.question.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Schema(description = "면접 문제 목록 Response")
@Getter
@Builder
public class QuestionsResponse {
    @Schema(description = "면접 문제 목록")
    private List<QuestionResponse> questions;

    @Schema(description = "현재 페이지", defaultValue = "1")
    private Integer currentPage;

    @Schema(description = "전체 페이지 개수")
    private Integer totalPageCount;

    public QuestionsResponse(List<QuestionResponse> questions, Integer currentPage, Integer totalPageCount) {
        this.questions = questions;
        this.currentPage = currentPage + 1;
        this.totalPageCount = totalPageCount;
    }
}
