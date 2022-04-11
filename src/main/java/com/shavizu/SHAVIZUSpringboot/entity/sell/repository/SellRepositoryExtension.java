package com.shavizu.SHAVIZUSpringboot.entity.sell.repository;

import com.shavizu.SHAVIZUSpringboot.dto.response.SellDetailsResponse;

public interface SellRepositoryExtension {
    SellDetailsResponse findBySellId(long sellId);
}
