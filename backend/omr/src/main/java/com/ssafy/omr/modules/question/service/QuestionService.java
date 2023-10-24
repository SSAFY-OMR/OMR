package com.ssafy.omr.modules.question.service;

import com.ssafy.omr.modules.answer.domain.Answer;
import com.ssafy.omr.modules.answer.repository.AnswerRepository;
import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.repository.MemberRepository;
import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.dto.QuestionDetailResponse;
import com.ssafy.omr.modules.question.dto.QuestionElement;
import com.ssafy.omr.modules.question.dto.QuestionsRequest;
import com.ssafy.omr.modules.question.dto.QuestionsResponse;
import com.ssafy.omr.modules.question.exception.InterviewQuestionNotFoundException;
import com.ssafy.omr.modules.question.mapper.QuestionMapper;
import com.ssafy.omr.modules.question.repository.InterviewQuestionRepository;
import com.ssafy.omr.modules.scrap.repository.InterviewQuestionScrapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private final InterviewQuestionRepository interviewQuestionRepository;
    private final InterviewQuestionScrapRepository interviewQuestionScrapRepository;
    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;

    private final QuestionMapper questionMapper;

    @Transactional(readOnly = true)
    public QuestionsResponse getQuestionsByCategory(AuthInfo authInfo, QuestionsRequest questionsRequest) {
        PageRequest pageRequest = questionsRequest.toPageRequest();
        Page<QuestionElement> questionElements = interviewQuestionRepository.findQuestionsByCategory(InterviewCategory.ofName(questionsRequest.getCategory()), pageRequest);
        return questionMapper.supplyQuestionsResponse(questionElements);
    }

    @Transactional(readOnly = true)
    public QuestionDetailResponse getQuestionById(AuthInfo authInfo, Long questionId) {
        InterviewQuestion interviewQuestion = interviewQuestionRepository.findById(questionId)
                .orElseThrow(InterviewQuestionNotFoundException::new);

        boolean isScrapped = false;
        String answer = null;

        Optional<Member> optionalMember = memberRepository.findById(authInfo.id());
        if (optionalMember.isPresent()) {
            isScrapped = interviewQuestionScrapRepository.existsByInterviewQuestionAndMember(interviewQuestion, optionalMember.get());

            Optional<Answer> optionalAnswer = answerRepository.findByInterviewQuestionAndMemberOfRecent(interviewQuestion, optionalMember.get());
            if (optionalAnswer.isPresent()) {
                answer = optionalAnswer.get().getContent();
            }
        }

        return questionMapper.supplyQuestionDetailResponse(interviewQuestion, isScrapped, answer);
    }
}
