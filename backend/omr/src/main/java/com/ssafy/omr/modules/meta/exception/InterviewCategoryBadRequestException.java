package com.ssafy.omr.modules.meta.exception;

import com.ssafy.omr.modules.global.exception.BadRequestException;

public class InterviewCategoryBadRequestException extends BadRequestException {
    private static final String MESSAGE = "잘못된 면접 카테고리입니다.";

    public InterviewCategoryBadRequestException() {
        super(MESSAGE);
    }
}
