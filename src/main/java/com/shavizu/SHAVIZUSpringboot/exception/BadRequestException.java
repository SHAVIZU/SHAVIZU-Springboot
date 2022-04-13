package com.shavizu.SHAVIZUSpringboot.exception;

import com.shavizu.SHAVIZUSpringboot.exception.handler.ShavizuException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ShavizuException {

    public static final BadRequestException EXCEPTION = new BadRequestException("Bad Request");

    private BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }

}
