package com.shavizu.SHAVIZUSpringboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StyleCodeSearchResponse {

    private final List<StyleCodeSearchDto> items;

}
