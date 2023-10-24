package com.ssafy.omr.modules.answer.exception;

import com.ssafy.omr.modules.global.exception.NotFoundException;

public class AnswerNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 답변입니다.";

    public AnswerNotFoundException() {
        super(MESSAGE);
    }
}
