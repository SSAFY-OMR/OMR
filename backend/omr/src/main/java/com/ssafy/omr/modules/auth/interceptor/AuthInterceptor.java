package com.ssafy.omr.modules.auth.interceptor;

import com.ssafy.omr.modules.auth.token.AuthorizationExtractor;
import com.ssafy.omr.modules.auth.token.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        }

        if(isPostMethodQuestions(request)) {
            return true;
        }

        if(isGetMethodMyProfile(request)) {
            String token = AuthorizationExtractor.extractAccessToken(request);
            return isValidToken(token, request, response);
        }

        if(isGetMethodHistory(request)) {
            String token = AuthorizationExtractor.extractAccessToken(request);
            return isValidToken(token, request, response);
        }

        if(isGetMethodQuestionDetail(request)) {
            String token = AuthorizationExtractor.extractAccessToken(request);
            return isValidToken(token, request, response);
        }

        if (isGetMethod(request)) {
            LOGGER.info("GET" + request.getRequestURI());
            return true;
        }

        if (notExistHeader(request)) {
            LOGGER.info("no header" + request.getRequestURI());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        String token = AuthorizationExtractor.extractAccessToken(request);

        return isValidToken(token, request, response);
    }

    private boolean isGetMethodMyProfile(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("GET") && request.getRequestURI().contains("/members/my-profile");
    }

    private boolean isGetMethodHistory(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("GET") && request.getRequestURI().contains("/history");
    }

    private boolean isGetMethodQuestionDetail(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("GET") && request.getRequestURI().contains("/questions/detail");
    }

    private boolean isGetMethod(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("GET");
    }

    private boolean isPostMethodQuestions(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("POST") && request.getRequestURI().contains("/questions");
    }

    private boolean notExistHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        return Objects.isNull(authorizationHeader);
    }

    private boolean isValidToken(String token, HttpServletRequest request, HttpServletResponse response) {
        if(tokenProvider.isValid(token)) {
            return true;
        } else {
            LOGGER.info("no token" + request.getRequestURI());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }
}
