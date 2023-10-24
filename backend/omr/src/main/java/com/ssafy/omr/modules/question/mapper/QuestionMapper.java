package com.ssafy.omr.modules.question.mapper;

import com.ssafy.omr.modules.question.dto.GetQuestionsResponse;
import com.ssafy.omr.modules.question.dto.QuestionElement;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionMapper {

    public GetQuestionsResponse supplyGetQuestionsResponse(Page<QuestionElement> questionElements) {
        return GetQuestionsResponse.builder()
                .questions(questionElements.getContent())
                .currentPage(questionElements.getNumber())
                .totalPageCount(questionElements.getTotalPages())
                .build();
    }
}
