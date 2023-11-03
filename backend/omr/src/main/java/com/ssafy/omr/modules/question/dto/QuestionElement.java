package com.ssafy.omr.modules.question.dto;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionElement {
    private Long questionId;
    private InterviewCategory category;
    private String content;

    public QuestionElement(Long questionId, InterviewCategory category, String content) {
        this.questionId = questionId;
        this.category = category;
        this.content = content;
    }
}
