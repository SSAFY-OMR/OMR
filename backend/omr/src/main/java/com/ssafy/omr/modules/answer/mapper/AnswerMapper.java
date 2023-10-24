package com.ssafy.omr.modules.answer.mapper;

import com.ssafy.omr.modules.answer.domain.Answer;
import com.ssafy.omr.modules.answer.dto.CreateAnswerRequest;
import com.ssafy.omr.modules.answer.dto.CreateAnswerResponse;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;

public class AnswerMapper {

    public static CreateAnswerResponse supplyCreateAnswerResponseFrom(Answer createdAnswer) {
        return new CreateAnswerResponse(
                createdAnswer.getId(),
                createdAnswer.getContent()
        );
    }

    public static Answer supplyAnswerOf(Member member, InterviewQuestion interviewQuestion, CreateAnswerRequest createAnswerRequest) {
        return Answer.builder()
                .member(member)
                .interviewQuestion(interviewQuestion)
                .content(createAnswerRequest.content())
                .build();
    }
}
