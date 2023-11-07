package com.ssafy.omr.modules.answer.dto;

import java.util.List;

public record AnswerListResponse(
        List<AnswerResponse> answerResponses,
        int currentPage,
        int totalPageCount
) {
}
