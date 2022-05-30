package com.shavizu.SHAVIZUSpringboot.exception.handler;

import com.shavizu.SHAVIZUSpringboot.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class ShavizuExceptionHandler {

    @ExceptionHandler(ShavizuException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(final ShavizuException e) {
        log.error(e.getLocalizedMessage());
        return new ResponseEntity<>(new ErrorResponse(e.getStatus(), e.getLocalizedMessage()), HttpStatus.valueOf(e.getStatus()));
    }

    //@Valid 어노테이션을 통과하지 못할 때
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.error(e.getCause().getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    //request에서 직접 받은 enum값이 잘못 되었을 때
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        log.error(e.getCause().getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    //데이터 무결성이 위반되었을 때
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ErrorResponse> handleConflictException(final DataIntegrityViolationException e) {
        log.error(e.getCause().getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.CONFLICT.value(), e.getLocalizedMessage()), HttpStatus.CONFLICT);
    }

}