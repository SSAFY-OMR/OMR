package com.ssafy.omr.modules.answer.service;

import com.ssafy.omr.modules.answer.domain.Answer;
import com.ssafy.omr.modules.answer.dto.CreateAnswerRequest;
import com.ssafy.omr.modules.answer.dto.CreateAnswerResponse;
import com.ssafy.omr.modules.answer.mapper.AnswerMapper;
import com.ssafy.omr.modules.answer.repository.AnswerRepository;
import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.global.event.createdAnswerEvent;
import com.ssafy.omr.modules.question.exception.InterviewQuestionNotFoundException;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.repository.MemberRepository;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.repository.InterviewQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;
    private final InterviewQuestionRepository interViewQuestionRepository;

    /**
     * 특정 문제에 답변을 작성한다.
     * 문제를 푼 사람의 id를 기반으로 createdAnswer 이벤트를 발생시킨다.
     *
     * @param authInfo            로그인된 유저 정보
     * @param createAnswerRequest 신규 답변 정보가 포함된 객체.
     * @return 신규 작성된 답변의 ID가 포함된 dto 응답객체
     */
    @Transactional
    public CreateAnswerResponse createAnswer(AuthInfo authInfo, CreateAnswerRequest createAnswerRequest) {
        Member memberRef = memberRepository.getReferenceById(authInfo.id());
        InterviewQuestion interviewQuestionRef = interViewQuestionRepository
                .findById(createAnswerRequest.questionId())
                .orElseThrow(InterviewQuestionNotFoundException::new);

        Answer createdAnswer = answerRepository.save(
                AnswerMapper.supplyAnswerOf(memberRef, interviewQuestionRef, createAnswerRequest)
        );
        applicationEventPublisher.publishEvent(new createdAnswerEvent(authInfo.id()));
        return AnswerMapper.supplyCreateAnswerResponseFrom(createdAnswer);
    }
}
