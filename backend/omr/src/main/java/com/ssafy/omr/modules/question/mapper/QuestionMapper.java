package com.ssafy.omr.modules.question.mapper;

import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.dto.QuestionDetailResponse;
import com.ssafy.omr.modules.question.dto.QuestionElement;
import com.ssafy.omr.modules.question.dto.QuestionsResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionMapper {

    public QuestionsResponse supplyQuestionsResponse(Page<QuestionElement> questionElements) {
        return QuestionsResponse.builder()
                .questions(questionElements.getContent())
                .currentPage(questionElements.getNumber())
                .totalPageCount(questionElements.getTotalPages())
                .build();
    }

    public QuestionDetailResponse supplyQuestionDetailResponse(InterviewQuestion interviewQuestion, Boolean isScrapped, String answer) {
        return QuestionDetailResponse.builder()
                .content(interviewQuestion.getContent())
                .isScraped(isScrapped)
                .answer(answer)
                .build();
    }
}
