package com.bssm.interceptor.common.config.security;

import com.bssm.interceptor.common.config.security.jwt.JwtExceptionCode;
import com.bssm.interceptor.common.config.security.jwt.JwtProperty;
import com.bssm.interceptor.app.web.path.ApiPath;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Objects;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException {
        JwtExceptionCode exceptionCode = (JwtExceptionCode) request.getAttribute(
            JwtProperty.JWT_EXCEPTION);
        String encode = URLEncoder.encode("비정상적인 접근입니다.", "UTF-8");
        if (Objects.nonNull(exceptionCode)) {
            encode = URLEncoder.encode(exceptionCode.getMessage(), "UTF-8");
        }
        response.sendRedirect(ApiPath.ERROR_AUTH + "?message=" + encode);
    }
}
