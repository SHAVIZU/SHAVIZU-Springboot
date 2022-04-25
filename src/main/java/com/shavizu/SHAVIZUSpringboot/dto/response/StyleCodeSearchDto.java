package com.shavizu.SHAVIZUSpringboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StyleCodeSearchDto {

    private final Long id;

    private final String name;

    private final String brand;

    private final String styleCode;

}
