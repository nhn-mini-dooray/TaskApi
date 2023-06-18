package com.nhnacademy.mini_dooray.task_api.advice;


import com.nhnacademy.mini_dooray.task_api.advice.error_response.ErrorResponse;
import com.nhnacademy.mini_dooray.task_api.exception.NotFoundException;
import com.nhnacademy.mini_dooray.task_api.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestCommonAdvice {

    @ExceptionHandler(value = {
            NotFoundException.class,
            EmptyResultDataAccessException.class,
            ValidationFailedException.class})
    public ResponseEntity<ErrorResponse> BadRequestExceptionHandler(Exception exception) {
        log.info("advice_Error_Message: {}", exception.getMessage(), exception);

        ErrorResponse response =
                new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), "Bad request");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
