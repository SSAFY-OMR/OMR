package com.ssafy.omr.modules.meta.mapper;

import java.util.Arrays;
import java.util.List;

import com.ssafy.omr.modules.meta.domain.MetaData;
import com.ssafy.omr.modules.meta.dto.MetaDataElement;
import com.ssafy.omr.modules.meta.dto.MetaDataResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MetaDataMapper {

	public static MetaDataElement supplyMetaDataElementFrom(MetaData metaDatum) {
		return new MetaDataElement(metaDatum.getId(), metaDatum.getName(), metaDatum.getDescription());
	}

	public static MetaDataResponse suppleMetaDataResponseFrom(MetaData[] metaData) {
		List<MetaDataElement> metaDataElements = Arrays
			.stream(metaData)
			.map(MetaDataMapper::supplyMetaDataElementFrom)
			.toList();

		return new MetaDataResponse(metaDataElements);
	}
}
