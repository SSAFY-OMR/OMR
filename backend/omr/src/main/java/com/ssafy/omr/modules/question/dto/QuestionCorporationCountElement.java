package com.ssafy.omr.modules.question.dto;

import com.ssafy.omr.modules.meta.domain.CorporationType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionCorporationCountElement {
    private CorporationType corporationType;
    private Long count;

    public QuestionCorporationCountElement(CorporationType corporationType, Long count) {
        this.corporationType = corporationType;
        this.count = count;
    }
}
