package com.bssm.interceptor.common.config.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bssm.interceptor.common.config.security.jwt.JwtProperty.JWT_EXCEPTION;

@RequiredArgsConstructor
public class JwtOncePerRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Value("${spring.security.jwt.header}")
    private String header;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        try {
            authenticate(request);
        } catch (ExpiredJwtException e) {
            request.setAttribute(JWT_EXCEPTION, JwtExceptionCode.EXPIRED);
        } catch (UnsupportedJwtException e) {
            request.setAttribute(JWT_EXCEPTION, JwtExceptionCode.UNSUPPORTED);
        } catch (MalformedJwtException e) {
            request.setAttribute(JWT_EXCEPTION, JwtExceptionCode.MALFORMED);
        } catch (SignatureException e) {
            request.setAttribute(JWT_EXCEPTION, JwtExceptionCode.INVALID_SIGNATURE);
        } catch (IllegalArgumentException e) {
            request.setAttribute(JWT_EXCEPTION, JwtExceptionCode.INVALID);
        }

        filterChain.doFilter(request, response);

    }

    private void authenticate(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (Strings.isNotBlank(token) && jwtTokenProvider.validateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

}
