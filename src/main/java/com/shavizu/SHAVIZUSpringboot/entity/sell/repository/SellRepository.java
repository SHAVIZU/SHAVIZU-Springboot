package com.shavizu.SHAVIZUSpringboot.entity.sell.repository;

import com.shavizu.SHAVIZUSpringboot.entity.sell.Sell;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import org.apache.catalina.LifecycleState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SellRepository extends CrudRepository<Sell, Long>, SellRepositoryExtension {
    Optional<Sell> findByIdAndShop(Long id, Shop shop);
    List<Sell> findByShop(Shop shop);
}
