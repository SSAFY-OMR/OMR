package com.ssafy.omr.modules.member.service;

import com.ssafy.omr.modules.auth.util.Encryptor;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.dto.ChangeEmojiRequest;
import com.ssafy.omr.modules.member.dto.MemberProfileResponse;
import com.ssafy.omr.modules.member.dto.SignUpRequest;
import com.ssafy.omr.modules.member.exception.MemberNotFoundException;
import com.ssafy.omr.modules.member.mapper.MemberMapper;
import com.ssafy.omr.modules.member.repository.MemberRepository;
import com.ssafy.omr.modules.util.RandomUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final RandomUtil randomUtil;
    private final Encryptor encryptor;

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

    @Transactional
    public void signUp(SignUpRequest signUpRequest) {

        String loginId = encryptor.encrypt(signUpRequest.loginId());
        String password = encryptor.encrypt(signUpRequest.password());

        Member member = MemberMapper.supplyUserOf(loginId, password, signUpRequest.emoji(), randomUtil.generateNickname());

        memberRepository.save(member);
    }
}
