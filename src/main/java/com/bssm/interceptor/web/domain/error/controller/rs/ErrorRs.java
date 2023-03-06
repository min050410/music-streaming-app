package com.bssm.interceptor.web.domain.error.controller.rs;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorRs {
    private HttpStatus httpStatus;
    private String message;
}