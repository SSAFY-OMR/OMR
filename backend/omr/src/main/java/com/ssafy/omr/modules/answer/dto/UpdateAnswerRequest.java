package com.ssafy.omr.modules.answer.dto;

public record UpdateAnswerRequest(
        Long answerId,
        String content
) {
}
