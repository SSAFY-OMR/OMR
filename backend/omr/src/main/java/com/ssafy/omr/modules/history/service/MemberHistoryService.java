package com.ssafy.omr.modules.history.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.omr.modules.history.repository.MemberQuestionHistoryRepository;
import com.ssafy.omr.modules.question.dto.QuestionElement;
import com.ssafy.omr.modules.question.dto.QuestionsRequest;
import com.ssafy.omr.modules.question.dto.QuestionsResponse;
import com.ssafy.omr.modules.question.mapper.QuestionMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberHistoryService {

	private final MemberQuestionHistoryRepository memberQuestionHistoryRepository;

	@Transactional(readOnly = true)
	public QuestionsResponse findScrapedQuestion(Long memberId, QuestionsRequest questionsRequest) {
		Page<QuestionElement> scrapedQuestions = memberQuestionHistoryRepository.findByScrapedQuestion(memberId,
			questionsRequest.getCategory(), questionsRequest.toPageRequest());

		return QuestionMapper.supplyQuestionsResponse(scrapedQuestions);
	}

	@Transactional(readOnly = true)
	public QuestionsResponse findSolvedQuestion(Long memberId, QuestionsRequest questionsRequest) {
		Page<QuestionElement> solvedQuestions = memberQuestionHistoryRepository.findBySolvedQuestion(memberId,
			questionsRequest.getCategory(), questionsRequest.toPageRequest());

		return QuestionMapper.supplyQuestionsResponse(solvedQuestions);
	}
}
