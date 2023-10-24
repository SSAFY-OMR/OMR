package com.ssafy.omr.modules.member.service;

import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.dto.ChangeEmojiRequest;
import com.ssafy.omr.modules.member.dto.MemberProfileResponse;
import com.ssafy.omr.modules.member.exception.MemberNotFoundException;
import com.ssafy.omr.modules.member.mapper.MemberMapper;
import com.ssafy.omr.modules.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberProfileResponse getMyProfileInformation(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        return MemberMapper.supplyMemberProfileResponseFrom(member);
    }

    @Transactional
    public void changeMemberEmoji(Long memberId, ChangeEmojiRequest changeEmojiRequest) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        member.changeEmoji(changeEmojiRequest.emoji());
    }
}
