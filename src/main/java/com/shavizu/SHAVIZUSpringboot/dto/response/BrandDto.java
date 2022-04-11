package com.shavizu.SHAVIZUSpringboot.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandDto {

    private final long id;

    private final String name;

}
