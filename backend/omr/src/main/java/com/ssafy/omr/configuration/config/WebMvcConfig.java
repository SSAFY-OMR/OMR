package com.ssafy.omr.configuration.config;

import com.ssafy.omr.modules.auth.interceptor.AuthInterceptor;
import com.ssafy.omr.modules.auth.token.AuthenticationPrincipalArgumentResolver;
import com.ssafy.omr.modules.auth.token.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,DELETE,TRACE,OPTIONS,PATCH,PUT";

    private final AuthInterceptor authInterceptor;
    private final TokenProvider tokenProvider;

    public WebMvcConfig(AuthInterceptor authInterceptor, TokenProvider tokenProvider) {
        this.authInterceptor = authInterceptor;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000",
                        "http://omrcs.com",
                        "http://www.omrcs.com",
                        "https://omrcs.com",
                        "https://www.omrcs.com")
                .allowedMethods(ALLOWED_METHOD_NAMES.split(","))
                .allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders(HttpHeaders.LOCATION, HttpHeaders.AUTHORIZATION, "Refresh-Token");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/refresh")
                .excludePathPatterns("/members/signup/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authenticationPrincipalArgumentResolver());
    }

    @Bean
    public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver() {
        return new AuthenticationPrincipalArgumentResolver(tokenProvider);
    }
}
