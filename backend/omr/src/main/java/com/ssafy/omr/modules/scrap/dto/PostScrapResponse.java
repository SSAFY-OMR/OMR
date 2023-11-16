package com.ssafy.omr.modules.scrap.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PostScrapResponse(
	@Schema(description = "스크랩 여부")
	boolean isScrapped
) {}
