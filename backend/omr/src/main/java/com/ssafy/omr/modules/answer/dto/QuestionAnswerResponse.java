package com.ssafy.omr.modules.answer.dto;

import org.springframework.data.domain.Page;

public record QuestionAnswerResponse(
        long questionId,
        Page<AnswerResponse> answerResponses
) {
}
