package com.ssafy.omr.modules.meta.domain;

import com.ssafy.omr.modules.meta.exception.MetaDataBadRequestException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum InterviewCategory {
    DATA_STRUCTURE(1, "DATA_STRUCTURE", "자료구조"),
    ALGORITHM(2, "ALGORITHM", "알고리즘"),
    COMPUTER_ARCHITECTURE(3, "COMPUTER_ARCHITECTURE", "컴퓨터구조"),
    NETWORK(4, "NETWORK", "네트워크"),
    OPERATING_SYSTEM(5, "OPERATING_SYSTEM", "운영체제"),
    DATABASE(6, "DATABASE", "데이터베이스"),
    JAVA(7, "JAVA", "자바"),
    SPRING(8, "SPRING", "스프링"),
    JAVASCRIPT(9, "JAVASCRIPT", "자바스크립트"),
    TYPESCRIPT(10, "TYPESCRIPT", "타입스크립트"),
    REACT(11, "REACT", "리액트"),
    VUE(12, "VUE", "뷰"),
    PYTHON(13, "PYTHON", "파이썬"),
    GIT(14, "GIT", "깃"),
    DOCKER(15, "DOCKER", "도커"),
    CLOUD(16, "CLOUD", "클라우드"),
    WEB(17, "WEB", "웹");

    private final Integer id;
    private final String name;
    private final String description;

    InterviewCategory(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static InterviewCategory ofName(String name) {
        return Arrays.stream(InterviewCategory.values())
                .filter(value -> value.getName().equals(name))
                .findAny()
                .orElseThrow(MetaDataBadRequestException::new);
    }
}
