package com.ssafy.omr.modules.question.mapper;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.question.domain.DailyQuestion;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.dto.DailyQuestionResponse;
import com.ssafy.omr.modules.question.dto.QuestionDetailResponse;
import com.ssafy.omr.modules.question.dto.QuestionElement;
import com.ssafy.omr.modules.question.dto.QuestionsResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionMapper {

    public static QuestionsResponse supplyQuestionsResponseTemp(Page<InterviewQuestion> interviewQuestions) {
        return QuestionsResponse.builder()
                .questions(interviewQuestions.getContent().stream()
                        .map(interviewQuestion -> QuestionElement.builder()
                                .questionId(interviewQuestion.getId())
                                .category(interviewQuestion.getInterviewCategory())
                                .content(interviewQuestion.getContent())
                                .corporationTypes(interviewQuestion.getCorporationTypes())
                                .build())
                        .collect(Collectors.toList()))
                .currentPage(interviewQuestions.getNumber())
                .totalPageCount(interviewQuestions.getTotalPages())
                .build();
    }

    public static QuestionsResponse supplyQuestionsResponse(Page<QuestionElement> questionElements) {
        return QuestionsResponse.builder()
                .questions(questionElements.getContent())
                .currentPage(questionElements.getNumber())
                .totalPageCount(questionElements.getTotalPages())
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

    public static DailyQuestionResponse supplyDailyQuestionResponse(InterviewQuestion interviewQuestion) {
        return DailyQuestionResponse.builder()
                .questionId(interviewQuestion.getId())
                .category(interviewQuestion.getInterviewCategory())
                .content(interviewQuestion.getContent())
                .build();
    }

    public static DailyQuestionResponse supplyDailyQuestionResponse(DailyQuestion dailyQuestion) {
        return DailyQuestionResponse.builder()
                .questionId(dailyQuestion.getInterviewQuestionId())
                .category(dailyQuestion.getInterviewCategory())
                .content(dailyQuestion.getContent())
                .build();
    }

    public static DailyQuestion supplyDailyQuestion(Integer seed, InterviewQuestion interviewQuestion) {
        return DailyQuestion.builder()
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
}
