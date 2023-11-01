package com.ssafy.omr.modules.history.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.auth.token.LoginUser;
import com.ssafy.omr.modules.history.service.MemberHistoryService;
import com.ssafy.omr.modules.question.dto.QuestionsRequest;
import com.ssafy.omr.modules.question.dto.QuestionsResponse;
import com.ssafy.omr.modules.util.base.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "member history", description = "사용자 CS 질문 스크랩 및 답변 API")
@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
@Slf4j
public class MemberHistoryController {

	private final MemberHistoryService memberHistoryService;

	@GetMapping("/questions/scraped")
	@Operation(
		summary = "사용자 스크랩 조회",
		description = "사용자가 스크랩한 CS질문 목록을 조회합니다. (offset 기반 페이지네이션)"
	)
	public BaseResponse<QuestionsResponse> findScrapedQuestion(@Parameter(hidden = true) @LoginUser AuthInfo authInfo, QuestionsRequest questionsRequest) {
		return BaseResponse.<QuestionsResponse>builder()
			.data(memberHistoryService.findScrapedQuestion(authInfo.id(), questionsRequest))
			.build();
	}

	@GetMapping("/questions/solved")
	@Operation(
		summary = "사용자 푼 문제 조회",
		description = "사용자가 답변한 CS질문 목록을 조회합니다. (offset 기반 페이지네이션)"
	)
	public BaseResponse<QuestionsResponse> findSolvedQuestion(@Parameter(hidden = true) @LoginUser AuthInfo authInfo, QuestionsRequest questionsRequest) {
		return BaseResponse.<QuestionsResponse>builder()
			.data(memberHistoryService.findSolvedQuestion(authInfo.id(), questionsRequest))
			.build();
	}
}
