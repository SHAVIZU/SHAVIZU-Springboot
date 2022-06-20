package com.shavizu.SHAVIZUSpringboot.entity.sell.repository;

import com.shavizu.SHAVIZUSpringboot.dto.response.SellDetailsResponse;
import com.shavizu.SHAVIZUSpringboot.entity.sell.Sell;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;

import java.util.List;

public interface SellRepositoryExtension {
    SellDetailsResponse findBySellId(long sellId);
    List<Sell> findAllByShop(Shop s);
}
