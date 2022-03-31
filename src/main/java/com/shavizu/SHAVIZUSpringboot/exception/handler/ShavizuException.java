package com.shavizu.SHAVIZUSpringboot.exception.handler;

import lombok.Getter;

@Getter
public class ShavizuException extends RuntimeException{

    private final int status;
    private final String message;

    protected ShavizuException(Integer status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
