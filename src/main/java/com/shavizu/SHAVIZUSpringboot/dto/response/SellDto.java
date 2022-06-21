package com.shavizu.SHAVIZUSpringboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SellDto {

    private final int price;

    private final int discountRate;

    private final int discountPrice;

}
