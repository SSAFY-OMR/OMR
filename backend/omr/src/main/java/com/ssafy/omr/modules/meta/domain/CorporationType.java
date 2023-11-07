package com.ssafy.omr.modules.meta.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.omr.modules.meta.exception.CorporationTypeBadRequestException;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CorporationType implements MetaData {
    NAVER(1, "NAVER", "네이버"),
    KAKAO(2, "KAKAO", "카카오"),
    WOOWA_BROTHERS(3, "WOOWA_BROTHERS", "우아한형제들"),
    LINE_PLUS(4, "LINE_PLUS", "라인플러스"),
    SAMSUNG(5, "SAMSUNG", "삼성");

    private final Integer id;
    private final String name;
    private final String description;

    CorporationType(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static CorporationType ofName(String name) {
        return Arrays.stream(CorporationType.values())
                .filter(value -> value.getName().equals(name))
                .findAny()
                .orElseThrow(CorporationTypeBadRequestException::new);
    }
}
