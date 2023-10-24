package com.ssafy.omr.modules.member.controller;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.auth.token.LoginUser;
import com.ssafy.omr.modules.member.dto.ChangeEmojiRequest;
import com.ssafy.omr.modules.member.dto.MemberProfileResponse;
import com.ssafy.omr.modules.member.service.MemberService;
import com.ssafy.omr.modules.util.base.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/my-profile")
    public BaseResponse<MemberProfileResponse> getMyProfileInformation(@LoginUser AuthInfo authInfo) {
        return BaseResponse.<MemberProfileResponse>builder()
                .data(memberService.getMyProfileInformation(authInfo.getId()))
                .build();
    }

    @PatchMapping("/emoji")
    public BaseResponse<Void> changeMemberEmoji(
            @LoginUser AuthInfo authInfo,
            @Valid @RequestBody ChangeEmojiRequest changeEmojiRequest) {
        memberService.changeMemberEmoji(authInfo.getId(), changeEmojiRequest);
        return BaseResponse.<Void>builder().build();
    }
}
