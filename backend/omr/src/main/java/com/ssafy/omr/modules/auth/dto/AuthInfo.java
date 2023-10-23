package com.ssafy.omr.modules.auth.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AuthInfo {
    private Long id;
    private String role;
    private String nickname;
}
