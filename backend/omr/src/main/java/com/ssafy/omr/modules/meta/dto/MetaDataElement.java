package com.ssafy.omr.modules.meta.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record MetaDataElement(
	@Schema(description = "메타 데이터 ID (미사용)")
	int id,
	@Schema(description = "메타 코드명")
	String name,
	@Schema(description = "메타데이터 설명")
	String description
) {}
