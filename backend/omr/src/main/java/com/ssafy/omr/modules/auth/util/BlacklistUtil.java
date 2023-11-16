package com.ssafy.omr.modules.auth.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class BlacklistUtil {

    private final StringRedisTemplate blacklistTemplate;

    private static final String BLACKLIST_IDENTIFIER = "blacklist:";
    private static final String BLACKLIST_VALUE = "AT";

    public void setBlacklist(String key, long expiration) {
        blacklistTemplate.opsForValue().set(BLACKLIST_IDENTIFIER + key, BLACKLIST_VALUE, expiration, TimeUnit.MILLISECONDS);
    }

    public boolean isBlacklist(String key) {

        return Boolean.TRUE.equals(blacklistTemplate.hasKey(BLACKLIST_IDENTIFIER + key));
    }
}
