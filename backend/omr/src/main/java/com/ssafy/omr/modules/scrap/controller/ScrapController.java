package com.ssafy.omr.modules.scrap.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.auth.token.LoginUser;
import com.ssafy.omr.modules.scrap.dto.PostScrapResponse;
import com.ssafy.omr.modules.scrap.service.ScrapService;
import com.ssafy.omr.modules.util.base.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ScrapController {

	private final ScrapService scrapService;

	@PostMapping("/questions/{questionId}/scrap")
	@Operation(
		summary = "사용자 CS질문 스크랩 토글",
		description = "사용자가 CS질문을 스크랩합니다. 이미 스크랩한 CS질문에 대해서는 스크랩을 취소합니다."
	)
	public BaseResponse<PostScrapResponse> toggleInterviewQuestionScrap(
		@Parameter(hidden = true) @LoginUser AuthInfo authInfo,
		@Parameter(description = "cs질문 pk")@PathVariable Long questionId
	) {
		return BaseResponse.<PostScrapResponse>builder()
			.data(scrapService.toggleInterviewQuestionScrap(questionId, Objects.requireNonNull(authInfo.id())))
			.build();
	}
}
