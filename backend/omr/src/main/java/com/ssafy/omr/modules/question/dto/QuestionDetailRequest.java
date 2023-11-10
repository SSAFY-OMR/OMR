package com.ssafy.omr.modules.question.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDetailRequest {
    @Schema(description = "전체 or 카테고리 인지 여부", allowableValues = {"0", "1"})
    private Boolean isAll;
}
