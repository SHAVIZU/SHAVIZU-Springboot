package com.shavizu.SHAVIZUSpringboot.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddSellRequest {

    @NotNull
    private SellDto sell;

    @NotNull
    private List<ItemDto> item;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SellDto {
        @NotNull
        private Integer price;

        @NotNull
        private Integer discountRate;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ItemDto {
        @NotNull
        private Long itemSizeId;

        @NotNull
        private Long amount;
    }

}
