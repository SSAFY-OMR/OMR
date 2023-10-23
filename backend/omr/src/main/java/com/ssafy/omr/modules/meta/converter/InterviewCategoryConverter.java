package com.ssafy.omr.modules.meta.converter;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import jakarta.persistence.AttributeConverter;

public class InterviewCategoryConverter implements AttributeConverter<InterviewCategory, String> {
    @Override
    public String convertToDatabaseColumn(InterviewCategory attribute) {
        return attribute.getName();
    }

    @Override
    public InterviewCategory convertToEntityAttribute(String dbData) {
        return InterviewCategory.ofName(dbData);
    }
}
