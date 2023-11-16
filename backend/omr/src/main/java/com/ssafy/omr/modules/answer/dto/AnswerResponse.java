package com.ssafy.omr.modules.answer.dto;

public record AnswerResponse(
        Long answerId,
        String nickname,
        String emoji,
        String content,
        boolean isLiked,
        Integer likeCount
) {
}
