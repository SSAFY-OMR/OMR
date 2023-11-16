package com.ssafy.omr.modules.answer.dto;

public record CreateAnswerRequest(
        Long questionId,
        String content
) {
}
