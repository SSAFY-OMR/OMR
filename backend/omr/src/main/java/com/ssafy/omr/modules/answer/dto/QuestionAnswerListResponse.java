package com.ssafy.omr.modules.answer.dto;

import java.util.List;

public record QuestionAnswerListResponse(
        List<AnswerResponse> answerResponses
) {
}
