package com.ssafy.omr.modules.answer.controller;

import com.ssafy.omr.modules.answer.dto.AnswerListResponse;
import com.ssafy.omr.modules.answer.dto.CreateAnswerRequest;
import com.ssafy.omr.modules.answer.dto.CreateAnswerResponse;
import com.ssafy.omr.modules.answer.dto.DeleteAnswerRequest;
import com.ssafy.omr.modules.answer.dto.QuestionAnswerResponse;
import com.ssafy.omr.modules.answer.dto.ToggleLikeAnswerRequest;
import com.ssafy.omr.modules.answer.dto.ToggleLikeAnswerResponse;
import com.ssafy.omr.modules.answer.dto.UpdateAnswerRequest;
import com.ssafy.omr.modules.answer.service.AnswerService;
import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.auth.token.LoginUser;
import com.ssafy.omr.modules.util.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {
  private final AnswerService answerService;
  private final int PAGE_SIZE = 5;

  @Operation(
          summary = "신규 답안 작성",
          description = "신규 답안을 작성합니다. 잘못된 문제 번호를 입력하면 동작하지 않습니다."
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "201",
                  description = "답변 작성 성공"
          )
  })
  @PostMapping("/create")
  public BaseResponse<CreateAnswerResponse> createAnswer(@Parameter(hidden = true) @LoginUser AuthInfo authInfo, @RequestBody CreateAnswerRequest createAnswerRequest) {
    CreateAnswerResponse createAnswerResponse = answerService.createAnswer(authInfo, createAnswerRequest);
    return BaseResponse.<CreateAnswerResponse>builder().data(createAnswerResponse).code("201").build();
  }

  @Operation(
          summary = "기존 답안 수정",
          description = "기존 답안을 수정합니다."
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "답변 수정 성공",
                  content = @Content()
          )
  })
  @PatchMapping("/update")
  public BaseResponse<Void> updateAnswer(@Parameter(hidden = true) @LoginUser AuthInfo authInfo, @RequestBody UpdateAnswerRequest updateAnswerRequest) {
    answerService.updateAnswer(authInfo, updateAnswerRequest);
    return BaseResponse.<Void>builder().build();
  }

  @Operation(
          summary = "답변을 삭제합니다.",
          description = "답변 번호와 신규 내용을 기입하여 수정"
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "답변 삭제 성공",
                  content = @Content()
          )
  })
  @DeleteMapping("/delete")
  public BaseResponse<Void> deleteAnswer(@Parameter(hidden = true) @LoginUser AuthInfo authInfo, @RequestBody DeleteAnswerRequest deleteAnswerRequest) {
    answerService.deleteAnswer(authInfo, deleteAnswerRequest);
    return BaseResponse.<Void>builder().build();
  }

  @Operation(
          summary = "특정 답안에 좋아요 처리",
          description = "답안 번호에 대해 좋아요 토글을 수행"
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "답변 좋아요 토글 성공",
                  content = @Content(schema = @Schema(implementation = ToggleLikeAnswerResponse.class))
          )
  })
  @PostMapping("/like")
  public BaseResponse<ToggleLikeAnswerResponse> toggleAnswerLike(
          @Parameter(hidden = true) @LoginUser AuthInfo authInfo,
          @RequestBody ToggleLikeAnswerRequest toggleLikeAnswerRequest
  ) {
    return BaseResponse.<ToggleLikeAnswerResponse>builder()
            .data(answerService.toggleAnswerLike(authInfo, toggleLikeAnswerRequest))
            .build();
  }

  @Operation(
          summary = "특정 문제에 대한 답변 목록 조회",
          description = "문제 번호와 페이징 정보를 입력받는다."
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "답변 목록 및 페이징 정보"
          )
  })
  @GetMapping("/question/{questionId}")
  public BaseResponse<QuestionAnswerResponse> getAnswerList(
          @PathVariable("questionId") Long questionId,
          @PageableDefault(size = PAGE_SIZE)
          @SortDefault.SortDefaults({
                  @SortDefault(sort = "likeCount", direction = Sort.Direction.DESC),
                  @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
          })
          Pageable pageable
  ) {
    return BaseResponse.<QuestionAnswerResponse>builder()
            .data(answerService.getAnswerList(questionId, pageable))
            .build();
  }

  @Operation(
          summary = "특정 문제에 대한 남의 답변 목록 조회",
          description = "내 답변을 제외한 타인의 답변만 조회."
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "답변 목록"
          )
  })
  @GetMapping("/question/{questionId}/others")
  public BaseResponse<AnswerListResponse> getOtherAnswerList(
          @PathVariable("questionId") Long questionId,
          @Parameter(hidden = true) @LoginUser AuthInfo authInfo
  ) {
    return BaseResponse.<AnswerListResponse>builder()
            .data(answerService.getOthersAnswerList(questionId, authInfo))
            .build();
  }

  @Operation(
          summary = "특정 문제에 대한 나의 답변 목록 조회",
          description = "내가 제출한 답변만 조회한다."
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "답변 목록"
          )
  })
  @GetMapping("/question/{questionId}/mine")
  public BaseResponse<AnswerListResponse> getMyAnswer(
          @PathVariable("questionId") Long questionId,
          @Parameter(hidden = true) @LoginUser AuthInfo authInfo
  ) {
    return BaseResponse.<AnswerListResponse>builder()
            .data(answerService.getMyAnswer(questionId, authInfo))
            .build();
  }


}
