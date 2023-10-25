package com.ssafy.omr.modules.auth.token;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import io.jsonwebtoken.Claims;

public interface TokenProvider {
    String createAccessToken(AuthInfo authInfo);

    String createRefreshToken(AuthInfo authInfo);

    Claims getPayload(String token);

    AuthInfo getParsedClaims(String token);

    boolean isValid(String token);

    Long parseRefreshToken(String token);

    Long getExpiration(String token);
}
