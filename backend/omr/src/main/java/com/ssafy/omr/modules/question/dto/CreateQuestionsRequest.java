package com.ssafy.omr.modules.question.dto;

import java.util.List;

public record CreateQuestionsRequest(
        List<String> contents,
        List<String> categories
) {
}
