package com.shavizu.SHAVIZUSpringboot.exception;

import com.shavizu.SHAVIZUSpringboot.exception.handler.ShavizuException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ShavizuException {

    public static final NotFoundException USER_NAME_NOT_FOUND = new NotFoundException("Username Not Found.");

    private NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }
}
