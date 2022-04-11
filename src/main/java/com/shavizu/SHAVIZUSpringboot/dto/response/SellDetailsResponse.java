package com.shavizu.SHAVIZUSpringboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SellDetailsResponse {

    private final SellDto sell;

    private final ItemDto itemDto;

    private final List<InventoryDto> inventories;

}
