package com.shavizu.SHAVIZUSpringboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ItemDetailsResponse {

    private final String imageUrl;

    private final List<SizeDto> size;

}
