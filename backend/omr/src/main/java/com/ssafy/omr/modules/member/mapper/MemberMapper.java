package com.ssafy.omr.modules.member.mapper;

import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.domain.RoleType;
import com.ssafy.omr.modules.member.dto.MemberProfileResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

    public static MemberProfileResponse supplyMemberProfileResponseFrom(Member member) {
        return new MemberProfileResponse(
                member.getLoginId(),
                member.getEmoji(),
                member.getNickname());
    }

    public static Member supplyUserOf(String loginId, String password, String emoji, String nickname) {
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .emoji(emoji)
                .nickname(nickname)
                .roleType(RoleType.USER)
                .build();
    }
}
