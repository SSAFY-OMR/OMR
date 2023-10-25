package com.ssafy.omr.modules.member.service;

import com.ssafy.omr.modules.auth.util.Encryptor;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.domain.MemberStreak;
import com.ssafy.omr.modules.member.dto.ChangeEmojiRequest;
import com.ssafy.omr.modules.member.dto.ChangePasswordRequest;
import com.ssafy.omr.modules.member.dto.MemberProfileResponse;
import com.ssafy.omr.modules.member.dto.MemberStreakResponse;
import com.ssafy.omr.modules.member.dto.SignUpRequest;
import com.ssafy.omr.modules.member.exception.MemberNotFoundException;
import com.ssafy.omr.modules.member.exception.SamePasswordException;
import com.ssafy.omr.modules.member.mapper.MemberMapper;
import com.ssafy.omr.modules.member.repository.MemberDynamicRepository;
import com.ssafy.omr.modules.member.repository.MemberRepository;
import com.ssafy.omr.modules.member.repository.MemberStreakRepository;
import com.ssafy.omr.modules.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberDynamicRepository memberDynamicRepository;
    private final MemberStreakRepository memberStreakRepository;
    private final RandomUtil randomUtil;
    private final Encryptor encryptor;

    @Transactional(readOnly = true)
    public MemberProfileResponse getMyProfileInformation(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        return MemberMapper.supplyMemberProfileResponseFrom(member);
    }

    @Transactional(readOnly = true)
    public MemberStreakResponse getStreaksByMonth(Long memberId, int year, int month) {
        return memberDynamicRepository.getMemberStreaksInformation(memberId, year, month);
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

        MemberStreak memberStreak = MemberStreak.builder().member(member).build();

        memberRepository.save(member);
        memberStreakRepository.save(memberStreak);
    }

    @Transactional
    public void changePassword(Long memberId, ChangePasswordRequest changePasswordRequest) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        String passwordToBe = encryptor.encrypt(changePasswordRequest.password());

        if (member.getPassword().equals(passwordToBe)) {
            throw new SamePasswordException();
        }

        member.changePassword(passwordToBe);
    }
}
