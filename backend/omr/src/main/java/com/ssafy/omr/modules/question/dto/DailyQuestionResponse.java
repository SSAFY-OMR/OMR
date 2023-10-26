package com.ssafy.omr.modules.question.dto;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyQuestionResponse {
    private InterviewCategory category;
    private String content;
}
