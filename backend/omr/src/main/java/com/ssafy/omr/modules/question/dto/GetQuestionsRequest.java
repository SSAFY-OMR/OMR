package com.ssafy.omr.modules.question.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetQuestionsRequest extends BasePageRequest{
    private String category;
}
