package com.bssm.interceptor.web.exception.handler;

import com.bssm.interceptor.web.endpoint.ErrorResponse;
import com.bssm.interceptor.web.exception.GeneralHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GeneralHttpException.class)
    public ResponseEntity<ErrorResponse> generalHttpExceptionHandler(GeneralHttpException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(e.getHttpStatus().value())
                .reason(e.getHttpStatus().getReasonPhrase())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .reason(e.getMessage())
                .message("Json 타입 메시지를 읽을 수 없습니다.")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .reason(e.getMessage())
                .message("형식에 맞지않은 요청입니다.")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .reason(e.getMessage())
                .message("알 수 없는 에러입니다. ")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
