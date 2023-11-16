package com.ssafy.omr.modules.answer.exception;

import com.ssafy.omr.modules.global.exception.ForbiddenException;
import com.ssafy.omr.modules.global.exception.UnauthorizedException;

public class AnswerForbiddenException extends ForbiddenException {

    public static final String MESSAGE = "해당 답변에 대한 변경 권한이 없습니다.";

    public AnswerForbiddenException() {
        super(MESSAGE);
    }
}
