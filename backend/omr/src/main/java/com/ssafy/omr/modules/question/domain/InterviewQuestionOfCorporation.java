package com.ssafy.omr.modules.question.domain;

import com.ssafy.omr.modules.meta.converter.CorporationTypeConverter;
import com.ssafy.omr.modules.meta.domain.CorporationType;
import com.ssafy.omr.modules.util.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "interview_question_of_corporation_id"))
@Entity
public class InterviewQuestionOfCorporation extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_question_id")
    private InterviewQuestion interviewQuestion;

    @NotNull
    @Column(length = 63)
    @Convert(converter = CorporationTypeConverter.class)
    private CorporationType corporationType;

}
