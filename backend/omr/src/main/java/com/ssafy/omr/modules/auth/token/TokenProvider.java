package com.ssafy.omr.modules.auth.token;

import com.ssafy.omr.modules.auth.dto.AuthInfo;

public interface TokenProvider {
    String createAccessToken(AuthInfo authInfo);

    String createRefreshToken();

    String getPayload(String token);

    AuthInfo getParsedClaims(String token);

    boolean isValid(String token);

    String createNewTokenWithNewNickname(String newNickname, AuthInfo authInfo);
}
