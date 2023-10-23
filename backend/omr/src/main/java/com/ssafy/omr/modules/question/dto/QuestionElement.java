package com.ssafy.omr.modules.question.dto;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionElement {
    private InterviewCategory category;
    private String content;

    public QuestionElement(InterviewCategory category, String content) {
        this.category = category;
        this.content = content;
    }
}
