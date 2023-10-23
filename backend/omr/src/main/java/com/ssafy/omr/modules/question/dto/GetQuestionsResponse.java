package com.ssafy.omr.modules.question.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class GetQuestionsResponse {
    private List<QuestionElement> questions;
    private Integer currentPage;
    private Integer totalPageCount;

    public static GetQuestionsResponse of(Page<QuestionElement> questionElements) {
        return GetQuestionsResponse.builder()
                .questions(questionElements.getContent())
                .currentPage(questionElements.getNumber() + 1)
                .totalPageCount(questionElements.getTotalPages())
                .build();
    }
}
