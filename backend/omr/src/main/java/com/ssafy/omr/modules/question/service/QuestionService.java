package com.ssafy.omr.modules.question.service;

import com.ssafy.omr.modules.answer.domain.Answer;
import com.ssafy.omr.modules.answer.repository.AnswerRepository;
import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.repository.MemberRepository;
import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.question.domain.DailyQuestion;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.dto.*;
import com.ssafy.omr.modules.question.exception.DailyQuestionNotFoundException;
import com.ssafy.omr.modules.question.exception.InterviewQuestionNotFoundException;
import com.ssafy.omr.modules.question.mapper.QuestionMapper;
import com.ssafy.omr.modules.question.repository.DailyQuestionRepository;
import com.ssafy.omr.modules.question.repository.InterviewQuestionRepository;
import com.ssafy.omr.modules.scrap.repository.InterviewQuestionScrapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private final InterviewQuestionRepository interviewQuestionRepository;
    private final InterviewQuestionScrapRepository interviewQuestionScrapRepository;
    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;
    private final DailyQuestionRepository dailyQuestionRepository;

    private final QuestionMapper questionMapper;

    @Transactional(readOnly = true)
    public QuestionsResponse getQuestionsByCategory(QuestionsRequest questionsRequest) {
        PageRequest pageRequest = questionsRequest.toPageRequest();

        String category = questionsRequest.getCategory();
        if (category == null) {
            Page<QuestionElement> questionElements = interviewQuestionRepository.findQuestions(pageRequest);
            return questionMapper.supplyQuestionsResponse(questionElements);
        }

        Page<QuestionElement> questionElements = interviewQuestionRepository.findQuestionsByCategory(InterviewCategory.ofName(category), pageRequest);
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

    @Transactional()
    public DailyQuestionResponse getDailyQuestion() {
        Integer seed = generateRandomSeed();

        Optional<DailyQuestion> cachedData = dailyQuestionRepository.findById(seed);
        if (cachedData.isPresent()) {
            return questionMapper.supplyDailyQuestionResponse(cachedData.get());
        }

        InterviewQuestion interviewQuestion = interviewQuestionRepository.findRandomQuestion(seed)
                .orElseThrow(DailyQuestionNotFoundException::new);

        DailyQuestion dailyQuestion = questionMapper.supplyDailyQuestion(seed, interviewQuestion);
        dailyQuestionRepository.save(dailyQuestion);

        return questionMapper.supplyDailyQuestionResponse(interviewQuestion);

    }

    private Integer generateRandomSeed() {
        LocalDate localDate = LocalDateTime.now()
                .minusHours(8)
                .minusMinutes(59)
                .minusSeconds(59)
                .toLocalDate();

        String seed = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return Integer.valueOf(seed);
    }
}
