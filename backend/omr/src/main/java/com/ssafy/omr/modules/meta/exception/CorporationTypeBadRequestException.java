package com.ssafy.omr.modules.meta.exception;

import com.ssafy.omr.modules.global.exception.BadRequestException;

public class CorporationTypeBadRequestException extends BadRequestException {

    private static final String MESSAGE = "잘못된 기업 입니다.";

    public CorporationTypeBadRequestException() {
        super(MESSAGE);
    }
}
