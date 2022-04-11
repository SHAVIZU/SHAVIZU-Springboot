package com.shavizu.SHAVIZUSpringboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemDto {

    private final String imageUrl;

    private final String brand;

    private final String name;

}
