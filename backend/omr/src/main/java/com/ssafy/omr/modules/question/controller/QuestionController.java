package com.ssafy.omr.modules.question.controller;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.util.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    @RequestMapping()
    public BaseResponse<Void> getQuestionsByCategory() {
        AuthInfo authInfo = new AuthInfo(1L, "user", "김싸피");
        return BaseResponse.<Void>builder()
                .data(null)
                .build();
    }
}
