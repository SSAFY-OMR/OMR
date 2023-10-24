package com.ssafy.omr.modules.meta.mapper;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.meta.dto.MetaDataElement;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MetaDataMapper {

	public static MetaDataElement supplyMetaDataElementFromCategory(InterviewCategory category) {
		return new MetaDataElement(category.getId(), category.getName(), category.getDescription());
	}
}
