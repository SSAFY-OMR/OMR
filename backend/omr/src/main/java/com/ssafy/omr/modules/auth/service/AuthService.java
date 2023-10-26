package com.ssafy.omr.modules.auth.service;

import com.ssafy.omr.modules.auth.domain.RefreshToken;
import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.auth.dto.ExistLoginIdResponse;
import com.ssafy.omr.modules.auth.dto.LoginRequest;
import com.ssafy.omr.modules.auth.dto.TokenResponse;
import com.ssafy.omr.modules.auth.exception.InvalidRefreshTokenException;
import com.ssafy.omr.modules.auth.exception.LoginFailedException;
import com.ssafy.omr.modules.auth.mapper.AuthMapper;
import com.ssafy.omr.modules.auth.repository.RefreshTokenRepository;
import com.ssafy.omr.modules.auth.token.TokenProvider;
import com.ssafy.omr.modules.auth.util.BlacklistUtil;
import com.ssafy.omr.modules.auth.util.Encryptor;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BlacklistUtil blacklistUtil;
    private final TokenProvider tokenProvider;
    private final Encryptor encryptor;

    @Transactional
    public TokenResponse login(LoginRequest loginRequest) {
        String loginId = encryptor.encrypt(loginRequest.loginId());
        String password = encryptor.encrypt(loginRequest.password());
        Member member = memberRepository.findByLoginIdAndPassword(loginId, password)
                .orElseThrow(LoginFailedException::new);

        AuthInfo authInfo = new AuthInfo(member.getId(), member.getRoleType().getName(), member.getNickname());

        String accessToken = tokenProvider.createAccessToken(authInfo);
        String refreshToken = tokenProvider.createRefreshToken(authInfo);

        // ASIS : redis에 refreshToken 저장
        refreshTokenRepository.save(new RefreshToken(loginId, refreshToken));

        return new TokenResponse(accessToken, refreshToken);
    }

    @Transactional
    public TokenResponse reissue(String token) {


        Long id = tokenProvider.parseRefreshToken(token);
        Member member = memberRepository.findById(id).orElseThrow(InvalidRefreshTokenException::new);

        RefreshToken refreshToken = refreshTokenRepository.findById(token)
                .orElseThrow(InvalidRefreshTokenException::new);

        if (!refreshToken.getLoginId().equals(member.getLoginId())) {
            throw new InvalidRefreshTokenException();
        }

        String accessToken = tokenProvider.createAccessToken(new AuthInfo(member.getId(), member.getRoleType().getName(), member.getNickname()));

        return new TokenResponse(accessToken,token);
    }

    @Transactional
    public void logout(Long memberId, String accessToken, String refreshToken) {

        if (!Objects.nonNull(memberId)) {
            throw new LoginFailedException();
        }

        memberRepository.findById(memberId).orElseThrow(LoginFailedException::new);

        Long expiresIn = tokenProvider.getExpiration(accessToken);

        blacklistUtil.setBlacklist(accessToken, expiresIn);

        refreshTokenRepository.deleteById(refreshToken);
    }

    @Transactional(readOnly = true)
    public ExistLoginIdResponse isExistLoginId(String loginId) {
        if(memberRepository.existsByLoginId(loginId)) {
            return AuthMapper.supplyExistLoginIdResponseFrom(true);
        }
        return AuthMapper.supplyExistLoginIdResponseFrom(false);
    }
}
