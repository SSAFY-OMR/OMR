package com.ssafy.omr.modules.member.exception;

import com.ssafy.omr.modules.global.exception.ForbiddenException;

public class SamePasswordException extends ForbiddenException {
    private static final String MESSAGE = "동일한 비밀번호로 변경할 수 없습니다.";

    public SamePasswordException() {
        super(MESSAGE);
    }
}
