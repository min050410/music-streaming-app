package com.bssm.interceptor.web.config.security;

import com.bssm.interceptor.web.config.security.jwt.JwtOncePerRequestFilter;
import com.bssm.interceptor.web.config.security.jwt.JwtTokenProvider;
import com.bssm.interceptor.web.path.ApiPath;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .httpBasic().disable()
                .cors().disable()
                .csrf().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()

                // ????????? ?????????
                .antMatchers(AUTH_WHITELIST).permitAll()

                // ?????? ?????????
                .antMatchers(ApiPath.ERROR_AUTH).permitAll()

                // ??????
                .antMatchers(ApiPath.MEMBER_LOGIN, ApiPath.MEMBER_SIGNUP).permitAll()
                .anyRequest().authenticated()
                .and()

                .addFilterBefore(new JwtOncePerRequestFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
                        .exceptionHandling()
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler);

        return http.build();
    }

    private static final String[] AUTH_WHITELIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/resources/**",
            "/",
    };

}
