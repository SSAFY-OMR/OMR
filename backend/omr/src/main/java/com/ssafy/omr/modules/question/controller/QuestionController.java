package com.ssafy.omr.modules.question.controller;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.auth.token.LoginUser;
import com.ssafy.omr.modules.question.dto.*;
import com.ssafy.omr.modules.question.service.QuestionService;
import com.ssafy.omr.modules.util.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QuestionService questionService;


    @Operation(summary = "카테고리 별 문제 목록 조회",
            description = "카테고리에 해당 하는 문제의 전체 목록을 조회합니다. 단, 카테고리가 입력되지 않으면 전체 문제 목록을 조회합니다." +
                    "(오프셋 기반 페이지네이션이 적용됩니다.)")
    @GetMapping()
    public BaseResponse<QuestionsResponse> getQuestionsByCategory(@Valid @ModelAttribute QuestionsRequest questionsRequest) {
        return BaseResponse.<QuestionsResponse>builder()
                .data(questionService.getQuestionsByCategory(questionsRequest))
                .build();
    }

    @Operation(summary = "문제 상세 조회",
            description = "id에 해당하는 단건 문제를 조회합니다.")
    @GetMapping("/{questionId}")
    public BaseResponse<QuestionDetailResponse> getQuestionById(@PathVariable Long questionId, @LoginUser AuthInfo authInfo) {
        return BaseResponse.<QuestionDetailResponse>builder()
                .data(questionService.getQuestionById(authInfo, questionId))
                .build();
    }

    @Operation(summary = "데일리 문제 조회",
            description = "매일 오전 9시마다 랜덤으로 정해지는 문제를 조회합니다.")
    @GetMapping("/daily")
    public BaseResponse<QuestionElement> getDailyQuestion() {
        return BaseResponse.<QuestionElement>builder()
                .data(questionService.getDailyQuestion())
                .build();
    }

    @Operation(summary = "문제 생성",
            description = "문제 content, 문제 분류를 지정하면 문제가 삽입됩니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(name = "CreateQuestionsRequest",
                                            value =
                                                    """  
                                                            { 
                                                                "contents": ["스택과 힙의 차이는 무엇일까요?"],
                                                                "category": "DATA_STRUCTURE"
                                                            } 
                                                            """,
                                            description = "contents: 해당 카테고리에 따른 질문 내용들, category: 카테고리"
                                    )}))
    )
    @PostMapping
    public BaseResponse<Void> createQuestions(
            @Valid @RequestBody CreateQuestionsRequest createQuestionsRequest
    ) {
        questionService.createQuestions(createQuestionsRequest);
        return BaseResponse.<Void>builder()
                .build();
    }

    @Operation(summary = "카테고리별 문제 개수 조회",
            description = "카테고리별로 문제개 몇개 존재하는지 개수를 조회합니다")
    @GetMapping("/count")
    public BaseResponse<QuestionCategoryCountResponse> getProblemCountsGroupByCategory() {
        return BaseResponse.<QuestionCategoryCountResponse>builder()
                .data(questionService.getProblemCountsGroupByCategory())
                .build();
    }
}