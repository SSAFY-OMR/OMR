package com.ssafy.omr.modules.meta.converter;

import com.ssafy.omr.modules.meta.domain.CorporationType;
import jakarta.persistence.AttributeConverter;

public class CorporationTypeConverter implements AttributeConverter<CorporationType, String> {

    @Override
    public String convertToDatabaseColumn(CorporationType attribute) {
        return attribute.getName();
    }

    @Override
    public CorporationType convertToEntityAttribute(String dbData) {
        return CorporationType.ofName(dbData);
    }
}
