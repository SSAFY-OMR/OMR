package com.ssafy.omr.modules.question.exception;

import com.ssafy.omr.modules.global.exception.NotFoundException;

public class DailyQuestionNotFoundException extends NotFoundException {
    private static final String MESSAGE = "오늘의 문제를 찾을 수 없습니다.";

    public DailyQuestionNotFoundException() {
        super(MESSAGE);
    }
}
