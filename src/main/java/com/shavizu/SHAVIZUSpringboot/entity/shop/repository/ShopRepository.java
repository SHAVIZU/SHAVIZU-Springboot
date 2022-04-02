package com.shavizu.SHAVIZUSpringboot.entity.shop.repository;

import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShopRepository extends CrudRepository<Shop, Long> {
    Optional<Shop> findByUserId(String userId);
}
