package com.ssafy.omr.modules.auth.token;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.auth.exception.InvalidRefreshTokenException;
import com.ssafy.omr.modules.auth.util.BlacklistUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider implements TokenProvider {

    private final Key signingKey;
    private final BlacklistUtil blacklistUtil;
    private final long validityInMilliseconds;
    private final long refreshTokenValidityMilliseconds;

    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") String secretKey,
                            @Value("${security.jwt.token.expire-length.access}") long validityInMilliseconds,
                            @Value("${security.jwt.token.expire-length.refresh}") long refreshTokenValidityMilliseconds,
                            BlacklistUtil blacklistUtil) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
        this.validityInMilliseconds = validityInMilliseconds;
        this.refreshTokenValidityMilliseconds = refreshTokenValidityMilliseconds;
        this.blacklistUtil = blacklistUtil;
    }

    @Override
    public String createAccessToken(AuthInfo authInfo) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .claim("id", authInfo.id())
                .claim("role", authInfo.role())
                .claim("nickname", authInfo.nickname())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(signingKey)
                .compact();
    }

    @Override
    public String createRefreshToken(AuthInfo authInfo) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenValidityMilliseconds);

        return Jwts.builder()
                .claim("id", authInfo.id())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(signingKey)
                .compact();
    }

    @Override
    public Claims getPayload(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public AuthInfo getParsedClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            Long id = ex.getClaims().get("id", Long.class);
            String role = ex.getClaims().get("role", String.class);
            String nickname = ex.getClaims().get("nickname", String.class);
            return new AuthInfo(id, role, nickname);
        }

        Long id = claims.get("id", Long.class);
        String role = claims.get("role", String.class);
        String nickname = claims.get("nickname", String.class);
        return new AuthInfo(id, role, nickname);
    }

    public Long parseRefreshToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            throw new InvalidRefreshTokenException();
        }

        Long id = claims.get("id", Long.class);
        return id;
    }

    @Override
    public boolean isValid(String token) {
        try {
            if (blacklistUtil.isBlacklist(token)) {
                throw new JwtException("로그아웃된 토큰입니다.");
            }

            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Long getExpiration(String token) {

        Date expiration = getPayload(token).getExpiration();

        return expiration.getTime() - new Date().getTime();
    }
}
