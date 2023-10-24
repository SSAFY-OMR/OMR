package com.ssafy.omr.modules.auth.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 604800000)
public class RefreshToken {

    @Id
    private String loginId;

    private String token;

    public RefreshToken(final String loginId, final String token) {
        this.loginId = loginId;
        this.token = token;
    }

}
