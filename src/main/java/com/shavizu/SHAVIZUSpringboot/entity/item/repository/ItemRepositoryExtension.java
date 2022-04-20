package com.shavizu.SHAVIZUSpringboot.entity.item.repository;

import com.shavizu.SHAVIZUSpringboot.dto.response.StyleCodeSearchResponse;

public interface ItemRepositoryExtension {
    StyleCodeSearchResponse findAllByStyleCode(String styleCode);
}
