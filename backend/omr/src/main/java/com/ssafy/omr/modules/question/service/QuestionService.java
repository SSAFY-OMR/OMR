package com.ssafy.omr.modules.question.service;

import com.ssafy.omr.modules.answer.domain.Answer;
import com.ssafy.omr.modules.answer.repository.AnswerRepository;
import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.repository.MemberRepository;
import com.ssafy.omr.modules.meta.domain.CorporationType;
import com.ssafy.omr.modules.question.domain.DailyQuestionRedis;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.domain.InterviewQuestionOfCorporation;
import com.ssafy.omr.modules.question.dto.*;
import com.ssafy.omr.modules.question.exception.DailyQuestionNotFoundException;
import com.ssafy.omr.modules.question.exception.InterviewQuestionNotFoundException;
import com.ssafy.omr.modules.question.mapper.QuestionMapper;
import com.ssafy.omr.modules.question.repository.DailyQuestionRedisRepository;
import com.ssafy.omr.modules.question.repository.InterviewQuestionOfCorporationRepository;
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
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private static final Integer MINUS_HOURS = 8;
    private static final Integer MINUS_MINUTES = 59;
    private static final Integer MINUS_SECONDS = 59;

    private final InterviewQuestionRepository interviewQuestionRepository;
    private final InterviewQuestionScrapRepository interviewQuestionScrapRepository;
    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;
    private final DailyQuestionRedisRepository dailyQuestionRedisRepository;
    private final InterviewQuestionOfCorporationRepository interviewQuestionOfCorporationRepository;

    @Transactional(readOnly = true)
    public QuestionsResponse getQuestionsByCategory(QuestionsRequest questionsRequest) {
        PageRequest pageRequest = questionsRequest.toPageRequest();

        String category = questionsRequest.getCategory();
        Page<InterviewQuestion> interviewQuestions = interviewQuestionRepository.findQuestionsByCategory(pageRequest, category);
        return QuestionMapper.supplyQuestionsResponse(interviewQuestions);
    }

    @Transactional(readOnly = true)
    public QuestionsResponse getQuestionByCorporation(QuestionsCorporationRequest questionsCorporationRequest) {
        PageRequest pageRequest = questionsCorporationRequest.toPageRequest();

        String corporation = questionsCorporationRequest.getCorporation();
        Page<InterviewQuestionOfCorporation> interviewQuestionOfCorporations = interviewQuestionOfCorporationRepository.findAllByCorporationType(CorporationType.ofName(corporation), pageRequest);
        return QuestionMapper.supplyQuestionsCorporationResponse(interviewQuestionOfCorporations);
    }

    @Transactional(readOnly = true)
    public QuestionDetailResponse getQuestionById(AuthInfo authInfo, Long questionId) {
        InterviewQuestion interviewQuestion = interviewQuestionRepository.findById(questionId)
                .orElseThrow(InterviewQuestionNotFoundException::new);

        InterviewQuestion nextQuestion = interviewQuestionRepository.findNextQuestion(interviewQuestion);

        boolean isScrapped = false;
        String answer = null;

        Optional<Member> optionalMember = Optional.empty();
        if (authInfo.id() != null) {
            optionalMember = memberRepository.findById(authInfo.id());
        }

        if (optionalMember.isPresent()) {
            isScrapped = interviewQuestionScrapRepository.existsByInterviewQuestionAndMember(interviewQuestion, optionalMember.get());

            Optional<Answer> optionalAnswer = answerRepository.findByInterviewQuestionAndMemberOfRecent(interviewQuestion, optionalMember.get());
            if (optionalAnswer.isPresent()) {
                answer = optionalAnswer.get().getContent();
            }
        }

        return QuestionMapper.supplyQuestionDetailResponse(interviewQuestion, isScrapped, answer, nextQuestion);
    }

    @Transactional()
    public QuestionResponse getDailyQuestion() {
        Integer seed = generateRandomSeed();

        Optional<DailyQuestionRedis> cachedData = dailyQuestionRedisRepository.findById(seed);
        if (cachedData.isPresent()) {
            return QuestionMapper.supplyQuestionResponse(cachedData.get());
        }

        InterviewQuestion interviewQuestion = interviewQuestionRepository.findRandomQuestion(seed)
                .orElseThrow(DailyQuestionNotFoundException::new);

        DailyQuestionRedis dailyQuestionRedis = QuestionMapper.supplyDailyQuestionRedis(seed, interviewQuestion);
        dailyQuestionRedisRepository.save(dailyQuestionRedis);

        return QuestionMapper.supplyQuestionResponse(interviewQuestion);

    }

    @Transactional
    public void createQuestions(CreateQuestionsRequest createQuestionsRequest) {
        List<InterviewQuestion> interviewQuestions = QuestionMapper.supplyInterviewQuestionEntityOf(
                createQuestionsRequest.category(), createQuestionsRequest.contents());

        interviewQuestionRepository.saveAll(interviewQuestions);
    }

    private Integer generateRandomSeed() {
        LocalDate localDate = LocalDateTime.now()
                .minusHours(MINUS_HOURS)
                .minusMinutes(MINUS_MINUTES)
                .minusSeconds(MINUS_SECONDS)
                .toLocalDate();

        String seed = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return Integer.valueOf(seed);
    }

    @Transactional(readOnly = true)
    public QuestionCategoryCountResponse getProblemCountsGroupByCategory() {
        List<QuestionCategoryCountElement> problemCounts = interviewQuestionRepository.findCategoryCount();
        return new QuestionCategoryCountResponse(problemCounts);
    }

    @Transactional(readOnly = true)
    public QuestionCorporationCountResponse getQuestionCountsByCorporation() {
        List<QuestionCorporationCountElement> questionCorporationCountElements = interviewQuestionOfCorporationRepository.findCorporationTypeCount();
        return QuestionMapper.supplyQuestionCorporationCountResponse(questionCorporationCountElements);
    }
}
