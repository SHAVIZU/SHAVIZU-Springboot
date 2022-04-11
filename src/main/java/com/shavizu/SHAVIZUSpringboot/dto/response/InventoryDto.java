package com.shavizu.SHAVIZUSpringboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InventoryDto {

    private final InventoryId id;

    private final String size;

    private final long amount;

    @Getter
    @AllArgsConstructor
    public static class InventoryId {

        private final long sellId;

        private final long itemSizeId;

    }

}
