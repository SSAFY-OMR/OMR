package com.ssafy.omr.modules.question.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class QuestionsResponse {
    private List<QuestionElement> questions;
    private Integer currentPage;
    private Integer totalPageCount;
}