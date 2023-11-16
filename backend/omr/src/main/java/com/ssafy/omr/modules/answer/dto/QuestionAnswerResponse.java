package com.ssafy.omr.modules.answer.dto;

import java.util.List;

public record QuestionAnswerResponse(
        long questionId,
        List<AnswerResponse> answerResponses,
        int currentPage,
        int totalPageCount

) {
}
