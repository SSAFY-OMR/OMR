package com.ssafy.omr.modules.interview.domain;

import com.ssafy.omr.modules.util.base.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id",column = @Column(name = "interview_question_id"))
@Entity
public class InterviewQuestion extends BaseEntity {

    @OneToMany(mappedBy = "interview_category_id")
    private List<InterviewCategory> interviewCategories = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String content;
}
