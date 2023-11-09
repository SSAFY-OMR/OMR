package com.ssafy.omr.modules.auth.exception;

import com.ssafy.omr.modules.global.exception.NotFoundException;

public class LoginFailedException extends NotFoundException {
    private static final String MESSAGE = "아이디나 비밀번호가 잘못되었거나 존재하지 않습니다";

    public LoginFailedException() {
        super(MESSAGE);
    }
}
