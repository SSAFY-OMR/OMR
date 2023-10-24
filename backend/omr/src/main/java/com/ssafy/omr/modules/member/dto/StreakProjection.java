package com.ssafy.omr.modules.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@Setter
@Getter

public class StreakProjection {
    private LocalDateTime localDateTime;
    private int count;
}
