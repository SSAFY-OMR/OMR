package com.ssafy.omr.modules.question.domain;

import com.ssafy.omr.modules.meta.converter.InterviewCategoryConverter;
import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.util.base.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "interview_question_id"))
@Entity
public class InterviewQuestion extends BaseEntity {

    @Column(length = 63)
    @Convert(converter = InterviewCategoryConverter.class)
    private InterviewCategory interviewCategory;

    @Column(columnDefinition = "TEXT")
    private String content;
}
