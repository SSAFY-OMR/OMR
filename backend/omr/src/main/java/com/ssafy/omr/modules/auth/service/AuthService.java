package com.ssafy.omr.modules.auth.service;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.auth.dto.LoginRequest;
import com.ssafy.omr.modules.auth.dto.TokenResponse;
import com.ssafy.omr.modules.auth.exception.LoginFailedException;
import com.ssafy.omr.modules.auth.token.TokenProvider;
import com.ssafy.omr.modules.auth.util.Encryptor;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final Encryptor encryptor;

    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequest loginRequest) {
        String username = encryptor.encrypt(loginRequest.loginId());
        String password = encryptor.encrypt(loginRequest.password());
        Member member = memberRepository.findByLoginIdAndPassword(username, password)
                .orElseThrow(LoginFailedException::new);

        AuthInfo authInfo = new AuthInfo(member.getId(), member.getRoleType().getName(), member.getNickname());

        String accessToken = tokenProvider.createAccessToken(authInfo);
        String refreshToken = tokenProvider.createRefreshToken(authInfo);

        // TODO : redis에 refreshToken 저장

        return new TokenResponse(accessToken, refreshToken);
    }

}
