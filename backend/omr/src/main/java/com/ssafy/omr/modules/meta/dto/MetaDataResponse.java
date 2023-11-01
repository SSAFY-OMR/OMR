package com.ssafy.omr.modules.meta.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "메타데이터 응답 Response")
public record MetaDataResponse(
	@Schema(description = "메타데이터 목록")
	List<MetaDataElement> metaData
) {}
