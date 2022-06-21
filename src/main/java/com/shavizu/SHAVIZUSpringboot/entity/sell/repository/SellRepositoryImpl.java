package com.shavizu.SHAVIZUSpringboot.entity.sell.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shavizu.SHAVIZUSpringboot.entity.sell.Sell;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.shavizu.SHAVIZUSpringboot.entity.brand.QBrand.brand;
import static com.shavizu.SHAVIZUSpringboot.entity.inventory.QInventory.inventory;
import static com.shavizu.SHAVIZUSpringboot.entity.item.QItem.item;
import static com.shavizu.SHAVIZUSpringboot.entity.item_size.QItemSize.itemSize;
import static com.shavizu.SHAVIZUSpringboot.entity.sell.QSell.sell;

@RequiredArgsConstructor
@Repository
public class SellRepositoryImpl implements SellRepositoryExtension {

    private final JPAQueryFactory jpaQuery;

    @Override
    public Sell findBySellId(Shop s, long sellId) {
        return jpaQuery.select(sell)
                .from(sell)
                .where(sell.shop.eq(s))
                .where(sell.id.eq(sellId))
                .join(sell.item, item).fetchJoin()
                .join(item.brand, brand).fetchJoin()
                .join(sell.inventories, inventory).fetchJoin()
                .join(inventory.itemSize, itemSize).fetchJoin()
                .fetchOne();
    }

    @Override
    public List<Sell> findAllByShop(Shop s) {
        return jpaQuery.select(sell)
                .from(sell).distinct()
                .where(sell.shop.eq(s))
                .join(sell.item, item).fetchJoin()
                .join(item.brand, brand).fetchJoin()
                .join(sell.inventories, inventory).fetchJoin()
                .join(inventory.itemSize, itemSize).fetchJoin()
                .fetch();
    }

}
