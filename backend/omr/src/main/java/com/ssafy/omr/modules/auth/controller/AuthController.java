package com.ssafy.omr.modules.auth.controller;

import com.ssafy.omr.modules.auth.dto.LoginRequest;
import com.ssafy.omr.modules.auth.dto.TokenResponse;
import com.ssafy.omr.modules.auth.service.AuthService;
import com.ssafy.omr.modules.auth.token.AuthorizationExtractor;
import com.ssafy.omr.modules.util.base.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public BaseResponse<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return BaseResponse.<TokenResponse>builder()
                .data(authService.login(loginRequest))
                .build();
    }

    @PostMapping("/reissue")
    public BaseResponse<TokenResponse> reissue(HttpServletRequest request) {
        return BaseResponse.<TokenResponse>builder()
                .data(authService.reissue(AuthorizationExtractor.extractRefreshToken(Objects.requireNonNull(request))))
                .build();
    }

}
