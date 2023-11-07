package com.ssafy.omr.modules.question.mapper;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.question.domain.DailyQuestionRedis;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.domain.InterviewQuestionOfCorporation;
import com.ssafy.omr.modules.question.dto.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionMapper {

    public static QuestionsResponse supplyQuestionsResponse(Page<InterviewQuestion> interviewQuestions) {
        return QuestionsResponse.builder()
                .questions(interviewQuestions.getContent().stream()
                        .map(interviewQuestion -> QuestionResponse.builder()
                                .questionId(interviewQuestion.getId())
                                .category(interviewQuestion.getInterviewCategory())
                                .content(interviewQuestion.getContent())
                                .build())
                        .collect(Collectors.toList()))
                .currentPage(interviewQuestions.getNumber())
                .totalPageCount(interviewQuestions.getTotalPages())
                .build();
    }

    public static QuestionsResponse supplyQuestionsCorporationResponse(Page<InterviewQuestionOfCorporation> interviewQuestionOfCorporations) {
        return QuestionsResponse.builder()
                .questions(interviewQuestionOfCorporations.getContent().stream()
                        .map(interviewQuestionOfCorporation -> QuestionResponse.builder()
                                .questionId(interviewQuestionOfCorporation.getInterviewQuestion().getId())
                                .category(interviewQuestionOfCorporation.getInterviewQuestion().getInterviewCategory())
                                .content(interviewQuestionOfCorporation.getInterviewQuestion().getContent())
                                .build())
                        .collect(Collectors.toList()))
                .currentPage(interviewQuestionOfCorporations.getNumber())
                .totalPageCount(interviewQuestionOfCorporations.getTotalPages())
                .build();
    }


    public static QuestionDetailResponse supplyQuestionDetailResponse(InterviewQuestion interviewQuestion, Boolean isScrapped, String answer) {
        return QuestionDetailResponse.builder()
                .category(interviewQuestion.getInterviewCategory())
                .content(interviewQuestion.getContent())
                .isScrapped(isScrapped)
                .answer(answer)
                .build();
    }

    public static QuestionResponse supplyQuestionResponse(InterviewQuestion interviewQuestion) {
        return QuestionResponse.builder()
                .questionId(interviewQuestion.getId())
                .category(interviewQuestion.getInterviewCategory())
                .content(interviewQuestion.getContent())
                .build();
    }

    public static QuestionResponse supplyQuestionResponse(DailyQuestionRedis dailyQuestionRedis) {
        return QuestionResponse.builder()
                .questionId(dailyQuestionRedis.getInterviewQuestionId())
                .category(dailyQuestionRedis.getInterviewCategory())
                .content(dailyQuestionRedis.getContent())
                .build();
    }

    public static DailyQuestionRedis supplyDailyQuestion(Integer seed, InterviewQuestion interviewQuestion) {
        return DailyQuestionRedis.builder()
                .id(seed)
                .interviewQuestionId(interviewQuestion.getId())
                .interviewCategory(interviewQuestion.getInterviewCategory())
                .content(interviewQuestion.getContent())
                .build();
    }

    public static List<InterviewQuestion> supplyInterviewQuestionEntityOf(String category, List<String> contents) {
        List<InterviewQuestion> interviewQuestions = new ArrayList<>();
        InterviewCategory interviewCategory = InterviewCategory.ofName(category);

        for (String content : contents) {
            interviewQuestions.add(InterviewQuestion.builder()
                    .interviewCategory(interviewCategory)
                    .content(content)
                    .build());
        }

        return interviewQuestions;
    }

    public static QuestionCorporationCountResponse supplyQuestionCorporationCountResponse(List<QuestionCorporationCountElement> questionCorporationCountElements) {
        return QuestionCorporationCountResponse.builder()
                .corporations(questionCorporationCountElements)
                .build();
    }
}
