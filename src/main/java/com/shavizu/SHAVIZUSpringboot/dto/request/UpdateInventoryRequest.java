package com.shavizu.SHAVIZUSpringboot.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateInventoryRequest {

    private List<InventoryDto> inventories;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class InventoryDto {

        @NotNull
        private Long itemSizeId;

        @NotNull
        private Long amount;

    }

}
