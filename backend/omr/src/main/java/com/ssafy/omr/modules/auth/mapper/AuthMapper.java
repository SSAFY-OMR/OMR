package com.ssafy.omr.modules.auth.mapper;

import com.ssafy.omr.modules.auth.dto.ExistLoginIdResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthMapper {

    public static ExistLoginIdResponse supplyExistLoginIdResponseFrom(boolean isExist) {
        return new ExistLoginIdResponse(isExist);
    }
}
