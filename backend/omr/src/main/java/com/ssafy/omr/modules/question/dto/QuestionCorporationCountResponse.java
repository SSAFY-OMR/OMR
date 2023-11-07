package com.ssafy.omr.modules.question.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QuestionCorporationCountResponse {
    @Schema(description = "기업별 문제 갯수")
    private List<QuestionCorporationCountElement> corporations;
}
