package com.bssm.interceptor.app.web.dto.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String message;

}