package com.ssafy.omr.modules.meta.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.meta.dto.MetaDataElement;
import com.ssafy.omr.modules.meta.dto.MetaDataResponse;
import com.ssafy.omr.modules.meta.mapper.MetaDataMapper;
import com.ssafy.omr.modules.util.base.BaseResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MetaDataController {

	@GetMapping("/category")
	public BaseResponse<MetaDataResponse> getInterviewCategories() {
		List<MetaDataElement> categories = Arrays.stream(InterviewCategory.values())
			.map(MetaDataMapper::supplyMetaDataElementFromCategory)
			.toList();

		return BaseResponse.<MetaDataResponse>builder()
			.data(new MetaDataResponse(categories))
			.build();
	}
}
