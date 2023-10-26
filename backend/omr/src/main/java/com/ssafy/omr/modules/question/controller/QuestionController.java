package com.ssafy.omr.modules.question.controller;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.auth.token.LoginUser;
import com.ssafy.omr.modules.question.dto.DailyQuestionResponse;
import com.ssafy.omr.modules.question.dto.QuestionDetailResponse;
import com.ssafy.omr.modules.question.dto.QuestionsRequest;
import com.ssafy.omr.modules.question.dto.QuestionsResponse;
import com.ssafy.omr.modules.question.service.QuestionService;
import com.ssafy.omr.modules.util.base.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping()
    public BaseResponse<QuestionsResponse> getQuestionsByCategory(@Valid @ModelAttribute QuestionsRequest questionsRequest) {
        return BaseResponse.<QuestionsResponse>builder()
                .data(questionService.getQuestionsByCategory(questionsRequest))
                .build();
    }

    @GetMapping("/{questionId}")
    public BaseResponse<QuestionDetailResponse> getQuestionById(@LoginUser AuthInfo authInfo, @PathVariable Long questionId) {
        return BaseResponse.<QuestionDetailResponse>builder()
                .data(questionService.getQuestionById(authInfo, questionId))
                .build();
    }

//    @Cacheable(value = "dailyQuestion", key = "1")
    @GetMapping("/daily")
    public BaseResponse<DailyQuestionResponse> getDailyQuestion() {
        return BaseResponse.<DailyQuestionResponse>builder()
                .data(questionService.getDailyQuestion())
                .build();
    }
}
