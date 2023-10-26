package com.ssafy.omr.modules.question.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Schema(description = "페이지 관련 DTO")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BasePageRequest {
    @Schema(description = "현재 페이지", defaultValue = "1", minimum = "1")
    @Builder.Default
    private Integer page = 1;

    @Schema(description = "페이지 크기", defaultValue = "10", minimum = "10")
    @Min(value = 10, message = "size가 너무 작습니다.")
    @Builder.Default
    private Integer size = 10;

    public void setPage(Integer page) {
        if (page <= 0) {
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public PageRequest toPageRequest() {
        return PageRequest.of(this.page - 1, this.size, Sort.Direction.DESC, "id");
    }
}
