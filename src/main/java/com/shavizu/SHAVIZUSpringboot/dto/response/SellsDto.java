package com.shavizu.SHAVIZUSpringboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SellsDto {

    private final int discountPrice;

    private final String itemName;

    private final String imageUrl;

    private final String brand;

    private final List<InventoryDto> inventories;

}
