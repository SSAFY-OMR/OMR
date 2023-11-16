package com.ssafy.omr.modules.util;

import com.ssafy.omr.modules.global.exception.ExternalLibraryException;
import com.ssafy.omr.modules.member.dto.NicknameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class RandomUtil {

    private final RestTemplate restTemplate;
    private static final String RANDOM_NICKNAME_GENERATOR_URL = "https://nickname.hwanmoo.kr/?format=json&max_length=12";
    public String generateNickname() {
        NicknameResponse nicknameResponse;

        try {
            nicknameResponse = restTemplate.getForObject(RANDOM_NICKNAME_GENERATOR_URL, NicknameResponse.class);
        } catch (Exception e) {
            throw new ExternalLibraryException("hwanmoo.kr");
        }

        return nicknameResponse.words().get(0);
    }
}
