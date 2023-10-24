package com.ssafy.omr.modules.member.mapper;


import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.dto.MemberProfileResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

    public static MemberProfileResponse supplyMemberProfileResponseFrom(Member member) {
        return new MemberProfileResponse(
                member.getLoginId(),
                member.getBoj_id(),
                member.getEmoji(),
                member.getNickname());
    }
}
