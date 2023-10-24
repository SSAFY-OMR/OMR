package com.ssafy.omr.modules.answer.exception;

import com.ssafy.omr.modules.global.exception.UnauthorizedException;

public class AnswerUnauthorizedException extends UnauthorizedException {
    public static final String MESSAGE = "필요한 회원 번호가 없습니다.";

    public AnswerUnauthorizedException() {
        super(MESSAGE);
    }
}
