package com.ssafy.omr.modules.answer.controller;

import com.ssafy.omr.modules.answer.dto.CreateAnswerResponse;
import com.ssafy.omr.modules.answer.service.AnswerService;
import com.ssafy.omr.modules.auth.token.LoginUser;
import com.ssafy.omr.modules.util.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/create")
    public BaseResponse<CreateAnswerResponse> createAnswer(@LoginUser LoginUser loginUser, Long questionId) {
        CreateAnswerResponse createAnswerResponse = answerService.createAnswer(loginUser, questionId);
        return BaseResponse.<CreateAnswerResponse>builder().data(createAnswerResponse).code("201").build();
    }

}
