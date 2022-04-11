package com.shavizu.SHAVIZUSpringboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SellDto {

    private final long price;

    private final int discountRate;

    private final long discountPrice;

}
