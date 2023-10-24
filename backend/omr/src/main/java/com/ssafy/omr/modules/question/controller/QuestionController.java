package com.ssafy.omr.modules.question.controller;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.question.dto.QuestionDetailResponse;
import com.ssafy.omr.modules.question.dto.QuestionsRequest;
import com.ssafy.omr.modules.question.dto.QuestionsResponse;
import com.ssafy.omr.modules.question.service.QuestionService;
import com.ssafy.omr.modules.util.base.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QuestionService questionService;

    @RequestMapping()
    public BaseResponse<QuestionsResponse> getQuestionsByCategory(@Valid @ModelAttribute QuestionsRequest questionsRequest) {
        AuthInfo authInfo = new AuthInfo(1L, "user", "김싸피");
        return BaseResponse.<QuestionsResponse>builder()
                .data(questionService.getQuestionsByCategory(authInfo, questionsRequest))
                .build();
    }

    @RequestMapping("/{questionId}")
    public BaseResponse<QuestionDetailResponse> getQuestionById(@PathVariable Long questionId){
        AuthInfo authInfo = new AuthInfo(1L, "user", "김싸피");
        return BaseResponse.<QuestionDetailResponse>builder()
                .data(questionService.getQuestionById(authInfo, questionId))
                .build();
    }
}
