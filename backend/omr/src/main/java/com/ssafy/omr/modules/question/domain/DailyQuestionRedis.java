package com.ssafy.omr.modules.question.domain;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(value = "dailyQuestion", timeToLive = 24 * 60 * 60)
public class DailyQuestionRedis {
    @Id
    private Integer id;

    private Long interviewQuestionId;

    private InterviewCategory interviewCategory;

    private String content;
}
