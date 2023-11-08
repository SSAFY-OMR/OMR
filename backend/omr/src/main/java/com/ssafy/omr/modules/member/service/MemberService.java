package com.ssafy.omr.modules.member.service;

import com.ssafy.omr.modules.auth.util.Encryptor;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.domain.MemberStreak;
import com.ssafy.omr.modules.member.domain.Streak;
import com.ssafy.omr.modules.member.dto.*;
import com.ssafy.omr.modules.member.exception.MemberNotFoundException;
import com.ssafy.omr.modules.member.exception.SamePasswordException;
import com.ssafy.omr.modules.member.mapper.MemberMapper;
import com.ssafy.omr.modules.member.mapper.StreakMapper;
import com.ssafy.omr.modules.member.repository.MemberDynamicRepository;
import com.ssafy.omr.modules.member.repository.MemberRepository;
import com.ssafy.omr.modules.member.repository.MemberStreakRepository;
import com.ssafy.omr.modules.member.repository.StreakRepository;
import com.ssafy.omr.modules.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberDynamicRepository memberDynamicRepository;
    private final MemberStreakRepository memberStreakRepository;
    private final StreakRepository streakRepository;
    private final RandomUtil randomUtil;
    private final Encryptor encryptor;

    @Transactional(readOnly = true)
    public MemberProfileResponse getMyProfileInformation(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        return MemberMapper.supplyMemberProfileResponseFrom(member, encryptor.decrypt(member.getLoginId()));
    }

    @Transactional
    public MemberStreakResponse getStreaksByMonth(Long memberId, int year, int month) {
        List<StreakElement> streakElements = memberDynamicRepository.getStreaksByMemberId(memberId, year, month);
        Member member = memberRepository.findMemberWithMemberStreakByMemberId(memberId)
                .orElseThrow(MemberNotFoundException::new);
        Map<LocalDate, Integer> map = new HashMap<>();

        for(StreakElement streakElement : streakElements) {
            map.put(streakElement.getLocalDate(), streakElement.getCount());
        }
        updateCurrentStreak(member.getMemberStreak());
        Integer currentStreak = member.getMemberStreak().getCurrentStreak();
        Integer longestStreak = member.getMemberStreak().getLongestStreak();


        return MemberMapper.supplyMemberStreakResponseOf(
                map,
                currentStreak,
                longestStreak);
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
        Member member = MemberMapper.supplyUserOf(
                loginId,
                password,
                signUpRequest.emoji(),
                randomUtil.generateNickname(),
                memberStreakRepository.save(MemberMapper.supplyMemberStreakEntity()));


        memberRepository.save(member);
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

    public void updateMemberStreakStatus(Long memberId) {
        LocalDate localDate = LocalDate.now();

        Streak streak = memberDynamicRepository.getTodayMemberStreak(memberId, localDate);
        if(Objects.isNull(streak)) { //오늘 문제를 푼 적이 없다면 new Streak 을 생성해서 저장
            Member member = memberRepository.findMemberWithMemberStreakByMemberId(memberId)
                    .orElseThrow(MemberNotFoundException::new);
            MemberStreak memberStreak = memberStreakRepository.findById(member.getMemberStreak().getId())
                    .orElseThrow(MemberNotFoundException::new);

            updateMemberStreak(localDate, memberStreak, member);
        } else { //오늘 문제를 푼 적이 있다면 solvedCount + 1
            streak.increaseSolvedCount();
        }
    }

    private void updateCurrentStreak(MemberStreak memberStreak) {
        LocalDate currentModifiedDate = memberStreak.getModifiedAt().toLocalDate();
        LocalDate localDate = LocalDate.now();
        Period period = Period.between(currentModifiedDate, localDate);

        if (isNotYesterDay(period)) {
            memberStreak.initializeCurrentStreak();
        }
    }

    private boolean isNotYesterDay(Period period) {
        return period.getYears() != 0 || period.getMonths() != 0 || period.getDays() > 1;
    }

    private void updateMemberStreak(LocalDate localDate, MemberStreak memberStreak, Member member) {
        streakRepository.save(StreakMapper.supplyStreakEntityOf(member, localDate));
        updateCurrentStreakAndLongestStreak(memberStreak);
    }

    private void updateCurrentStreakAndLongestStreak(MemberStreak memberStreak) {
        memberStreak.increaseCurrentStreak();
        updateLongestStreak(memberStreak);
    }

    private void updateLongestStreak(MemberStreak memberStreak) {
        if(memberStreak.getCurrentStreak() > memberStreak.getLongestStreak()) {
            memberStreak.updateLongestStreak();
        }
    }
}
