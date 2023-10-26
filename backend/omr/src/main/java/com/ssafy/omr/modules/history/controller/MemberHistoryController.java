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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
@Slf4j
public class MemberHistoryController {

	private final MemberHistoryService memberHistoryService;

	@GetMapping("/questions/scraped")
	public BaseResponse<QuestionsResponse> findScrapedQuestion(@LoginUser AuthInfo authInfo, QuestionsRequest questionsRequest) {
		return BaseResponse.<QuestionsResponse>builder()
			.data(memberHistoryService.findScrapedQuestion(authInfo.id(), questionsRequest))
			.build();
	}

	@GetMapping("/questions/solved")
	public BaseResponse<QuestionsResponse> findSolvedQuestion(@LoginUser AuthInfo authInfo, QuestionsRequest questionsRequest) {
		return BaseResponse.<QuestionsResponse>builder()
			.data(memberHistoryService.findSolvedQuestion(authInfo.id(), questionsRequest))
			.build();
	}
}
