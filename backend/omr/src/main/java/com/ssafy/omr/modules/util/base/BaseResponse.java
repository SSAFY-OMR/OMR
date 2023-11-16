package com.ssafy.omr.modules.util.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BaseResponse<T> {
    @Builder.Default
    private String code = "200";

    @Builder.Default
    private String message = "success";

    private T data;

    @JsonIgnore
    public static BaseResponse<Void> noContent() {
        return BaseResponse.<Void>builder().build();
    }
}
