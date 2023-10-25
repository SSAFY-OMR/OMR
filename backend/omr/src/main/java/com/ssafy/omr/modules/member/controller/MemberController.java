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

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public BaseResponse<Void> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        memberService.signUp(signUpRequest);
        return BaseResponse.<Void>builder().build();
    }

    @GetMapping("/my-profile")
    public BaseResponse<MemberProfileResponse> getMyProfileInformation(@LoginUser AuthInfo authInfo) {
        return BaseResponse.<MemberProfileResponse>builder()
                .data(memberService.getMyProfileInformation(authInfo.id()))
                .build();
    }

    @PatchMapping("/emoji")
    public BaseResponse<Void> changeMemberEmoji(
            @LoginUser AuthInfo authInfo,
            @Valid @RequestBody ChangeEmojiRequest changeEmojiRequest) {
        memberService.changeMemberEmoji(authInfo.id(), changeEmojiRequest);
        return BaseResponse.<Void>builder().build();
    }

    @GetMapping("/streak/{month}/{year}")
    public BaseResponse<MemberStreakResponse> getStreaksByMonth(
            @LoginUser AuthInfo authInfo,
            @PathVariable("month") @NotBlank @Size(min = 1, max = 12) int month,
            @PathVariable("year") @NotBlank @Size(min = 2023, max = 2024) int year) {
        return BaseResponse.<MemberStreakResponse>builder()
                .data(memberService.getStreaksByMonth(authInfo.id(), year, month))
                .build();
    }

    @PostMapping("/password")
    public BaseResponse<Void> updatePassword(@LoginUser AuthInfo authInfo,
                                             @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {

        memberService.changePassword(authInfo.id(), changePasswordRequest);
        return BaseResponse.<Void>builder().build();
    }
}
