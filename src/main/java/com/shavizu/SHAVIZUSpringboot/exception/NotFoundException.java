package com.shavizu.SHAVIZUSpringboot.exception;

import com.shavizu.SHAVIZUSpringboot.exception.handler.ShavizuException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ShavizuException {

    public static final NotFoundException USER_NAME_NOT_FOUND = new NotFoundException("Username not found.");

    public static final NotFoundException BRAND_NOT_FOUND = new NotFoundException("Brand not found.");

    public static final NotFoundException SELL_NOT_FOUND = new NotFoundException("Sell not found.");

    public static final NotFoundException INVENTORY_NOT_FOUND = new NotFoundException("Inventory not found.");

    public static final NotFoundException ITEM_NOT_FOUND = new NotFoundException("Item not found.");

    public static final NotFoundException ITEM_SIZE_NOT_FOUND = new NotFoundException("Item size not found.");

    private NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }

}
