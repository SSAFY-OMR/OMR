package com.ssafy.omr.modules.auth.token;

import com.ssafy.omr.modules.auth.dto.AuthInfo;

public interface TokenProvider {
    String createAccessToken(AuthInfo authInfo);

    String createRefreshToken(AuthInfo authInfo);

    String getPayload(String token);

    AuthInfo getParsedClaims(String token);

    boolean isValid(String token);

    Long parseRefreshToken(String token);
}
