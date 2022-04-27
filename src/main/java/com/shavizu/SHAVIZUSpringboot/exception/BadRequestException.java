package com.shavizu.SHAVIZUSpringboot.exception;

import com.shavizu.SHAVIZUSpringboot.exception.handler.ShavizuException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ShavizuException {

    public static final BadRequestException EXCEPTION = new BadRequestException("Bad Request");

    public static final BadRequestException FILE_EMPTY_EXCEPTION = new BadRequestException("File is empty exception.");

    public static final BadRequestException FILE_SAVE_FAILED_EXCEPTION = new BadRequestException("Failed to save file.");

    private BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }

}
