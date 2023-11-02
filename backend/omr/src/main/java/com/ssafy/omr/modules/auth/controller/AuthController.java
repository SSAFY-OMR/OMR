package com.ssafy.omr.modules.auth.controller;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.auth.dto.ExistLoginIdResponse;
import com.ssafy.omr.modules.auth.dto.LoginRequest;
import com.ssafy.omr.modules.auth.dto.TokenResponse;
import com.ssafy.omr.modules.auth.service.AuthService;
import com.ssafy.omr.modules.auth.token.AuthorizationExtractor;
import com.ssafy.omr.modules.auth.token.LoginUser;
import com.ssafy.omr.modules.util.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Tag(name = "auth", description = "인증 관련 api")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "로그인", description = "id, password 를 통한 로그인")
    @ApiResponse(
            responseCode = "200",
            description = "로그인 성공",
            content = @Content(schema = @Schema(implementation = TokenResponse.class))
    )
    @PostMapping("/login")
    public BaseResponse<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return BaseResponse.<TokenResponse>builder()
                .data(authService.login(loginRequest))
                .build();
    }

    @Operation(summary = "리이슈", description = "액세스 토큰 재발급")
    @ApiResponse(
            responseCode = "200",
            description = "리이슈 성공",
            content = @Content(schema = @Schema(implementation = TokenResponse.class))
    )
    @PostMapping("/reissue")
    public BaseResponse<TokenResponse> reissue(HttpServletRequest request) {
        return BaseResponse.<TokenResponse>builder()
                .data(authService.reissue(AuthorizationExtractor.extractRefreshToken(Objects.requireNonNull(request))))
                .build();
    }

    @Operation(summary = "로그아웃", description = "로그아웃")
    @ApiResponse(
            responseCode = "200",
            description = "로그아웃 성공"
    )
    @PostMapping("/logout")
    public BaseResponse<Void> logout(@Parameter(hidden = true) @LoginUser AuthInfo authInfo,
                                     HttpServletRequest request) {

        String accessToken = AuthorizationExtractor.extractAccessToken(Objects.requireNonNull(request));
        String refreshToken = AuthorizationExtractor.extractRefreshToken(Objects.requireNonNull(request));

        authService.logout(authInfo.id(), accessToken, refreshToken);

        return BaseResponse.noContent();
    }

    @Operation(summary = "아이디 중복 검사", description = "아이디 중복 검사")
    @ApiResponse(
            responseCode = "200",
            description = "아이디 중복 검사 성공",
            content = @Content(schema = @Schema(implementation = ExistLoginIdResponse.class))
    )
    @GetMapping("/existence")
    public BaseResponse<ExistLoginIdResponse> isExistLoginId(@NotNull
                                                                 @Length(min = 8, max = 16, message = "올바르지 않은 아이디 형식입니다.")
                                                                 @Pattern(regexp = "^[a-z]+[a-z0-9]{8,16}$", message = "올바르지 않은 아이디 형식입니다.")
                                                                 String loginId) {
        return BaseResponse.<ExistLoginIdResponse>builder()
                .data(authService.isExistLoginId(loginId))
                .build();
    }
}
