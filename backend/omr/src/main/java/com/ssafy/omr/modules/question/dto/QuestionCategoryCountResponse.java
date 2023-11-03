package com.ssafy.omr.modules.question.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCategoryCountResponse {
	@Schema(description = "카테고리별 문제 갯수")
	private List<QuestionCategoryCountElement> categoriesCount;
}
