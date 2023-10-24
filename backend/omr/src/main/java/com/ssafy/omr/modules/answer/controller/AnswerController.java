package com.ssafy.omr.modules.answer.controller;

import com.ssafy.omr.modules.answer.dto.CreateAnswerRequest;
import com.ssafy.omr.modules.answer.dto.CreateAnswerResponse;
import com.ssafy.omr.modules.answer.dto.UpdateAnswerRequest;
import com.ssafy.omr.modules.answer.service.AnswerService;
import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.auth.token.LoginUser;
import com.ssafy.omr.modules.util.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/create")
    public BaseResponse<CreateAnswerResponse> createAnswer(@LoginUser AuthInfo authInfo, @RequestBody CreateAnswerRequest createAnswerRequest) {
        CreateAnswerResponse createAnswerResponse = answerService.createAnswer(authInfo, createAnswerRequest);
        return BaseResponse.<CreateAnswerResponse>builder().data(createAnswerResponse).code("201").build();
    }

    @PatchMapping("/update")
    public BaseResponse<Void> updateAnswer(@RequestBody UpdateAnswerRequest updateAnswerRequest) {
        answerService.updateAnswer(updateAnswerRequest);
        return BaseResponse.<Void>builder().build();
    }


}
