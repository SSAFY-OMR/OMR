package com.ssafy.omr.modules.question.exception;
import com.ssafy.omr.modules.global.exception.NotFoundException;

public class InterviewQuestionNotFoundException extends NotFoundException {
    private static final String MESSAGE = "해당 번호의 문제가 없습니다.";

    public InterviewQuestionNotFoundException() {
        super(MESSAGE);
    }
}
