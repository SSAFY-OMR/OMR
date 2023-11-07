package com.ssafy.omr.modules.question.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Schema(description = "기업별 문제 목록 조회 Request")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsCorporationRequest extends BasePageRequest{
    @Schema(description = "기업", allowableValues = {"NAVER", "SAMSUNG"})
    private String corporation;
}
