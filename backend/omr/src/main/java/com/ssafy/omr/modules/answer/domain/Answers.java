package com.ssafy.omr.modules.answer.domain;

import com.ssafy.omr.modules.answer.dto.AnswerResponse;
import com.ssafy.omr.modules.answer.mapper.AnswerMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {
    private List<AnswerResponse> answers;

    public Answers(List<Answer> answerList, List<AnswerLike> answerLikes) {
        answers = new ArrayList<>();
        answers = answerList.stream()
                .map(answer -> AnswerMapper.supplyAnswerResponseOf(answer, isLiked(answerLikes, answer)))
                .collect(Collectors.toList());
    }

    private boolean isLiked(List<AnswerLike> answerLikes, Answer answer) {
        for (AnswerLike answerLike : answerLikes) {
            if(isEqualAnswerId(answerLike, answer)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEqualAnswerId(AnswerLike answerLike, Answer answer) {
        return Objects.equals(answerLike.getAnswer().getId(), answer.getId());
    }

    public List<AnswerResponse> getAnswers() {
        return answers;
    }
}
