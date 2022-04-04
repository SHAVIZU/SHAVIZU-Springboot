package com.shavizu.SHAVIZUSpringboot.exception;

import com.shavizu.SHAVIZUSpringboot.exception.handler.ShavizuException;
import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends ShavizuException {

    public static final UnAuthorizedException INVALID_TOKEN = new UnAuthorizedException("Invalid token.");

    public static final UnAuthorizedException EXPIRED_TOKEN = new UnAuthorizedException("Expired token.");

    public static final UnAuthorizedException NOT_AUTHENTICATED = new UnAuthorizedException("Not Authenticated.");

    private UnAuthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED.value(), message);
    }

}
