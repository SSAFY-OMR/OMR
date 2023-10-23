package com.ssafy.omr.modules.meta.exception;

import com.ssafy.omr.modules.global.exception.BadRequestException;

public class MetaDataBadRequestException extends BadRequestException {
    private static final String MESSAGE = "댓글은 1자 이상 255자 이하여야 합니다.";

    public MetaDataBadRequestException() {
        super(MESSAGE);
    }
}
