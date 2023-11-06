package com.ssafy.omr.modules.question.domain;

import com.ssafy.omr.modules.meta.domain.CorporationType;
import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@RedisHash(value = "dailyQuestion", timeToLive = 24 * 60 * 60)
public class DailyQuestion {
    @Id
    private Integer id;

    private Long interviewQuestionId;

    private InterviewCategory interviewCategory;

    private String content;

    private List<CorporationType> corporationTypes;

    public List<CorporationType> getCorporationTypes() {
        if (corporationTypes != null)
            return corporationTypes;
        return new ArrayList<>();
    }
}
