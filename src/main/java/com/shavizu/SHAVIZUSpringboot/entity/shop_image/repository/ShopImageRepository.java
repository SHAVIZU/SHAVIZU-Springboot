package com.shavizu.SHAVIZUSpringboot.entity.shop_image.repository;

import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.entity.shop_image.ShopImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShopImageRepository extends CrudRepository<ShopImage, Long> {
    List<ShopImage> findAllByShop(Shop shop);
}
