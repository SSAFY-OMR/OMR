package com.ssafy.omr.modules.member.dto;

import com.ssafy.omr.modules.member.validator.EmojiCheck;

public record ChangeEmojiRequest(
        @EmojiCheck
        String emoji) { }
