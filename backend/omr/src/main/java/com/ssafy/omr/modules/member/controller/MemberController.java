package com.ssafy.omr.modules.member.controller;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.auth.token.LoginUser;
import com.ssafy.omr.modules.member.dto.ChangeEmojiRequest;
import com.ssafy.omr.modules.member.dto.ChangePasswordRequest;
import com.ssafy.omr.modules.member.dto.MemberProfileResponse;
import com.ssafy.omr.modules.member.dto.MemberStreakResponse;
import com.ssafy.omr.modules.member.dto.SignUpRequest;
import com.ssafy.omr.modules.member.service.MemberService;
import com.ssafy.omr.modules.util.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "members", description = "회원 관련 api")
@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @Operation(
            summary = "회원가입",
            description = "회원가입 API입니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(name = "SignUpRequest",
                                            value =
                                                    """  
                                                    { 
                                                        "loginId" : "testtestid1",
                                                        "password" : "testtestpwd1",
                                                        "emoji": "😀"
                                                    } 
                                                    """,
                                            description = "loginId: 아이디, password: 비밀번호, emoji: 이모지"
                                    )}))
    )
    @PostMapping("/signup")
    public BaseResponse<Void> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        memberService.signUp(signUpRequest);
        return BaseResponse.<Void>builder().build();
    }

    @Operation(summary = "나의 프로필 조회", description = "나의 프로필 조회 API입니다.")
    @ApiResponse(
            responseCode = "200",
            description = "나의 프로필 조회 성공"
    )
    @GetMapping("/my-profile")
    public BaseResponse<MemberProfileResponse> getMyProfileInformation(
            @Parameter(hidden = true) @LoginUser AuthInfo authInfo) {
        return BaseResponse.<MemberProfileResponse>builder()
                .data(memberService.getMyProfileInformation(authInfo.id()))
                .build();
    }

    @Operation(
            summary = "프로필 이모지 변경",
            description = "나의 이모지 프로필 변경 API입니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(name = "ChangeEmojiRequest",
                                            value =
                                                    """  
                                                    { 
                                                        "emoji" : "😀"
                                                    } 
                                                    """,
                                            description = "emoji: 이모지"
                                    )})))
    @PatchMapping("/emoji")
    public BaseResponse<Void> changeMemberEmoji(
            @LoginUser AuthInfo authInfo,
            @Valid @RequestBody ChangeEmojiRequest changeEmojiRequest) {
        memberService.changeMemberEmoji(authInfo.id(), changeEmojiRequest);
        return BaseResponse.<Void>builder().build();
    }

    @Operation(
            summary = "나의 스트릭 조회",
            description = "나의 스트릭 조회 API입니다. 알고 싶은 month, year를 필수적으로 입력해야 합니다.",
            parameters = {
                    @Parameter(name = "month", description = "달", required = true),
                    @Parameter(name = "year", description = "년도", required = true)})
    @ApiResponse(
            responseCode = "200",
            description = "스트릭 조회 성공"
    )
    @GetMapping("/streak/{month}/{year}")
    public BaseResponse<MemberStreakResponse> getStreaksByMonth(
            @Parameter(hidden = true) @LoginUser AuthInfo authInfo,
            @PathVariable("month") @NotBlank @Size(min = 1, max = 12) int month,
            @PathVariable("year") @NotBlank @Size(min = 2023, max = 2024) int year) {
        return BaseResponse.<MemberStreakResponse>builder()
                .data(memberService.getStreaksByMonth(authInfo.id(), year, month))
                .build();
    }

    @Operation(
            summary = "나의 비밀번호 변경",
            description = "나의 비밀번호 변경 API입니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(name = "ChangePasswordRequest",
                                            value =
                                                    """  
                                                    { 
                                                        "password" : "testtestpwd1"
                                                    } 
                                                    """,
                                            description = "password: 비밀번호"
                                    )})))
    @PostMapping("/password")
    public BaseResponse<Void> updatePassword(@LoginUser AuthInfo authInfo,
                                             @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {

        memberService.changePassword(authInfo.id(), changePasswordRequest);
        return BaseResponse.<Void>builder().build();
    }
}
