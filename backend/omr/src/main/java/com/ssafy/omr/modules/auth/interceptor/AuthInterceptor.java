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
//        if (isGetMethodWithQuestionsUri(request) || isGetMethodWithAnswersUri(request)) {
//            return true;
//        }
        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        }

        if(isPostMethodQuestions(request)) {
            return true;
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
        if (isInvalidToken(token)) {
            LOGGER.info("no token" + request.getRequestURI());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        return true;
    }

    private boolean isGetMethod(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("GET");
    }

//    private boolean isGetMethodWithQuestionsUri(HttpServletRequest request) {
//        return request.getRequestURI().contains("/questions") && request.getMethod().equalsIgnoreCase("GET");
//    }
//
//    private boolean isGetMethodWithAnswersUri(HttpServletRequest request) {
//        return request.getRequestURI().contains("/answers") && request.getMethod().equalsIgnoreCase("GET");
//    }

    private boolean isPostMethodQuestions(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("POST") && request.getRequestURI().contains("/questions");
    }

    private boolean notExistHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        return Objects.isNull(authorizationHeader);
    }

    private boolean isInvalidToken(String token) {
        return !tokenProvider.isValid(token);
    }
}
