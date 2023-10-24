package com.ssafy.omr.modules.meta.dto;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;

public record MetaDataElement(
	int id,
	String name,
	String description
) {}
