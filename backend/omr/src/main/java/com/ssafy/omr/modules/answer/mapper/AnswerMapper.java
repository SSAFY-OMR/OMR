package com.ssafy.omr.modules.answer.mapper;

import com.ssafy.omr.modules.answer.domain.Answer;
import com.ssafy.omr.modules.answer.dto.CreateAnswerResponse;
import org.springframework.stereotype.Component;

public class AnswerMapper {

    public static CreateAnswerResponse supplyCreateAnswerResponseFrom(Answer createdAnswer) {
        return new CreateAnswerResponse(
                createdAnswer.getId(),
                createdAnswer.getContent()
        );
    }
}
