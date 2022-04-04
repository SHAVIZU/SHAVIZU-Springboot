package com.shavizu.SHAVIZUSpringboot.entity.sell;

import com.shavizu.SHAVIZUSpringboot.entity.BaseIdCreatedAtEntity;
import com.shavizu.SHAVIZUSpringboot.entity.inventory.Inventory;
import com.shavizu.SHAVIZUSpringboot.entity.item.Item;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_sell",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"shop_id", "item_id"})
})
public class Sell extends BaseIdCreatedAtEntity {

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer discountRate;

    @Column(nullable = false)
    private Integer discountPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Inventory> inventories = new ArrayList<>();

    public static Sell createSell(Integer price, Integer discountRate, Shop shop, Item item) {
        Sell sell = new Sell();
        sell.price = price;
        sell.discountRate = discountRate;
        sell.discountPrice = price * (100 - discountRate);
        sell.shop = shop;
        sell.item = item;

        return sell;
    }

}
