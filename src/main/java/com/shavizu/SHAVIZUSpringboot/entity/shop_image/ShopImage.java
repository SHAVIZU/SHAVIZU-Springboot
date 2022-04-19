package com.shavizu.SHAVIZUSpringboot.entity.shop_image;

import com.shavizu.SHAVIZUSpringboot.entity.BaseIdEntity;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_shop_image")
public class ShopImage extends BaseIdEntity {

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Integer sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    public static ShopImage createShopImage(String imageUrl, Integer sequence, Shop shop) {
        ShopImage shopImage = new ShopImage();
        shopImage.imageUrl = imageUrl;
        shopImage.sequence = sequence;
        shopImage.shop = shop;

        return shopImage;
    }

}
