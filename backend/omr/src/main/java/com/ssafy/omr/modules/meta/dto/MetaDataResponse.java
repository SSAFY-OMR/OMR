package com.ssafy.omr.modules.meta.dto;

import java.util.List;

public record MetaDataResponse(
	List<MetaDataElement> metaData
) {}
