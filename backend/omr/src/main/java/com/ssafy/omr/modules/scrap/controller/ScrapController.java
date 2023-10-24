package com.ssafy.omr.modules.scrap.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.auth.token.LoginUser;
import com.ssafy.omr.modules.scrap.dto.PostScrapResponse;
import com.ssafy.omr.modules.scrap.service.ScrapService;
import com.ssafy.omr.modules.util.base.BaseResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ScrapController {

	private final ScrapService scrapService;

	@PostMapping("/questions/{questionId}/scrap")
	public BaseResponse<PostScrapResponse> toggleInterviewQuestionScrap(@LoginUser AuthInfo authInfo, @PathVariable Long questionId) {
		return BaseResponse.<PostScrapResponse>builder()
			.data(scrapService.toggleInterviewQuestionScrap(questionId, authInfo.id()))
			.build();
	}
}
