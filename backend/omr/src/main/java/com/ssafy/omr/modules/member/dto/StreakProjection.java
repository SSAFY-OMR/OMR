package com.ssafy.omr.modules.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@NoArgsConstructor
@Setter
@Getter

public class StreakProjection {
    private LocalDate localDate;
    private int count;
}
