package com.shavizu.SHAVIZUSpringboot.exception;

import com.shavizu.SHAVIZUSpringboot.exception.handler.ShavizuException;
import org.springframework.http.HttpStatus;

public class ConflictException extends ShavizuException {

    public static final ConflictException ALREADY_EXISTS_SHOP = new ConflictException("Shop name or id already exists.");

    public static final ConflictException ALREADY_EXISTS_STYLE_CODE = new ConflictException("Style code already exists.");

    public static final ConflictException ALREADY_EXISTS_BRAND = new ConflictException("Brand already exists.");

    private ConflictException(String message) {
        super(HttpStatus.CONFLICT.value(), message);
    }

}
