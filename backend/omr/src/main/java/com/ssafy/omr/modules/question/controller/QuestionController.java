package com.ssafy.omr.modules.question.controller;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.question.dto.QuestionsRequest;
import com.ssafy.omr.modules.question.dto.GetQuestionsResponse;
import com.ssafy.omr.modules.question.service.QuestionService;
import com.ssafy.omr.modules.util.base.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QuestionService questionService;

    @RequestMapping()
    public BaseResponse<GetQuestionsResponse> getQuestionsByCategory(@Valid @ModelAttribute QuestionsRequest questionsRequest) {
        AuthInfo authInfo = new AuthInfo(1L, "user", "김싸피");
        return BaseResponse.<GetQuestionsResponse>builder()
                .data(questionService.getQuestionsByCategory(authInfo, questionsRequest))
                .build();
    }
}
