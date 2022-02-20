package com.example.config.exception.advisor;

import com.example.config.exception.model.ExceptionResponse;
import com.example.config.exception.types.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handlerDefaultException(Exception exception) {
        log.error("defaultException : ", exception);
        ExceptionResponse errorResult = ExceptionResponse.of(ErrorCode.ERROR);
        return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<ExceptionResponse> handlerException(ServiceException exception) {
        log.debug("ServiceException : ", exception);
        ExceptionResponse errorResult = ExceptionResponse.of(ErrorCode.serviceError(exception.getMessage()));
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<ExceptionResponse> handlerException(UsernameNotFoundException exception) {
        log.debug("UserException : ", exception);
        ExceptionResponse errorResult = ExceptionResponse.of(ErrorCode.userError(exception.getMessage()));
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

}
