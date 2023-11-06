package com.ssafy.omr.modules.question.dto;

import com.ssafy.omr.modules.meta.domain.CorporationType;
import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QuestionElement {
    private Long questionId;
    private InterviewCategory category;
    private String content;
    private List<CorporationType> corporationTypes;
}
