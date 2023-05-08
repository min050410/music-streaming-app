package com.bssm.interceptor.app.web.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private Integer statusCode;
    private String reason;
    private String message;

}