package com.ssafy.omr.modules.member.exception;

import com.ssafy.omr.modules.global.exception.NotFoundException;

public class MemberNotFoundException extends NotFoundException {

    private static final String MESSAGE = "해당 멤버가 존재하지 않습니다.";

    public MemberNotFoundException() {
        super(MESSAGE);
    }
}
