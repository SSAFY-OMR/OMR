package com.ssafy.omr.modules.member.dto;

import java.util.List;

public record NicknameResponse(
        List<String> words,
        String seed
) {
}
