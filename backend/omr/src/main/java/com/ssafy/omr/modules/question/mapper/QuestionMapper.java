package com.ssafy.omr.modules.question.mapper;

import com.ssafy.omr.modules.question.domain.DailyQuestion;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.dto.DailyQuestionResponse;
import com.ssafy.omr.modules.question.dto.QuestionDetailResponse;
import com.ssafy.omr.modules.question.dto.QuestionElement;
import com.ssafy.omr.modules.question.dto.QuestionsResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionMapper {

    public static QuestionsResponse supplyQuestionsResponse(Page<QuestionElement> questionElements) {
        return QuestionsResponse.builder()
                .questions(questionElements.getContent())
                .currentPage(questionElements.getNumber())
                .totalPageCount(questionElements.getTotalPages())
                .build();
    }

    public static QuestionDetailResponse supplyQuestionDetailResponse(InterviewQuestion interviewQuestion, Boolean isScrapped, String answer) {
        return QuestionDetailResponse.builder()
                .content(interviewQuestion.getContent())
                .isScraped(isScrapped)
                .answer(answer)
                .build();
    }

    public static DailyQuestionResponse supplyDailyQuestionResponse(InterviewQuestion interviewQuestion) {
        return DailyQuestionResponse.builder()
                .category(interviewQuestion.getInterviewCategory())
                .content(interviewQuestion.getContent())
                .build();
    }

    public static DailyQuestionResponse supplyDailyQuestionResponse(DailyQuestion dailyQuestion) {
        return DailyQuestionResponse.builder()
                .category(dailyQuestion.getInterviewCategory())
                .content(dailyQuestion.getContent())
                .build();
    }

    public static DailyQuestion supplyDailyQuestion(Integer seed, InterviewQuestion interviewQuestion) {
        return DailyQuestion.builder()
                .id(seed)
                .interviewCategory(interviewQuestion.getInterviewCategory())
                .content(interviewQuestion.getContent())
                .build();
    }
}
