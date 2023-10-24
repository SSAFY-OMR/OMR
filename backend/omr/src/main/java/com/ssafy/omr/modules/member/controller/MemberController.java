package com.ssafy.omr.modules.member.controller;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.auth.token.LoginUser;
import com.ssafy.omr.modules.member.dto.MemberProfileResponse;
import com.ssafy.omr.modules.member.service.MemberService;
import com.ssafy.omr.modules.util.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
