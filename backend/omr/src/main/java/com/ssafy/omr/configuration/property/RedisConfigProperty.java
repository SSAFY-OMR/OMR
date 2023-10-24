package com.ssafy.omr.configuration.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.redis.data")
public class RedisConfigProperty {
    private final String host;

    private final String password;

    private final int port;

}
