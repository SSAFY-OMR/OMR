package com.ssafy.omr.modules.scrap.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.exception.MemberNotFoundException;
import com.ssafy.omr.modules.member.repository.MemberRepository;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.repository.InterviewQuestionRepository;
import com.ssafy.omr.modules.scrap.domain.InterviewQuestionScrap;
import com.ssafy.omr.modules.scrap.dto.PostScrapResponse;
import com.ssafy.omr.modules.scrap.mapper.ScrapMapper;
import com.ssafy.omr.modules.scrap.repository.InterviewQuestionScrapRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScrapService {

	private final InterviewQuestionRepository questionRepository;
	private final InterviewQuestionScrapRepository scrapRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public PostScrapResponse toggleInterviewQuestionScrap(Long questionId, Long memberId) {
		Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
		InterviewQuestion interviewQuestion = questionRepository.findById(questionId).orElseThrow(RuntimeException::new);
		InterviewQuestionScrap scrap = scrapRepository.findByInterviewQuestionIdAndMemberId(questionId, memberId);
		if(scrap != null) {
			scrapRepository.delete(scrap);
		} else {
			saveNewInterviewQuestion(member, interviewQuestion);
		}

		return new PostScrapResponse(scrap != null);
	}

	private void saveNewInterviewQuestion(Member member, InterviewQuestion interviewQuestion) {
		InterviewQuestionScrap interviewQuestionScrap = ScrapMapper
			.supplyInterviewQuestionScrapOf(member, interviewQuestion);
		scrapRepository.save(interviewQuestionScrap);
	}
}
