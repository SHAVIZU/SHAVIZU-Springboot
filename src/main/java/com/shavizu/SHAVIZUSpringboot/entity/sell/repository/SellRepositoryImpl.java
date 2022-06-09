package com.shavizu.SHAVIZUSpringboot.entity.sell.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shavizu.SHAVIZUSpringboot.dto.response.InventoryDto;
import com.shavizu.SHAVIZUSpringboot.dto.response.ItemDto;
import com.shavizu.SHAVIZUSpringboot.dto.response.SellDetailsResponse;
import com.shavizu.SHAVIZUSpringboot.dto.response.SellDto;
import com.shavizu.SHAVIZUSpringboot.dto.response.SellInventoryDto;
import com.shavizu.SHAVIZUSpringboot.dto.response.SellsDto;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.core.types.Projections.constructor;
import static com.querydsl.core.types.Projections.list;
import static com.shavizu.SHAVIZUSpringboot.entity.brand.QBrand.brand;
import static com.shavizu.SHAVIZUSpringboot.entity.inventory.QInventory.inventory;
import static com.shavizu.SHAVIZUSpringboot.entity.item.QItem.item;
import static com.shavizu.SHAVIZUSpringboot.entity.item_size.QItemSize.itemSize;
import static com.shavizu.SHAVIZUSpringboot.entity.sell.QSell.sell;
import static com.shavizu.SHAVIZUSpringboot.entity.shop.QShop.shop;

@RequiredArgsConstructor
@Repository
public class SellRepositoryImpl implements SellRepositoryExtension {

    private final JPAQueryFactory jpaQuery;

    @Override
    public SellDetailsResponse findBySellId(long sellId) {
        return jpaQuery.select(
                constructor(
                        SellDetailsResponse.class,
                        constructor(SellDto.class,
                                sell.price,
                                sell.discountRate,
                                sell.discountPrice),
                        constructor(ItemDto.class,
                                item.imageUrl,
                                brand.name,
                                item.name),
                        list(
                                constructor(InventoryDto.class,
                                        constructor(InventoryDto.InventoryId.class,
                                                inventory.sellId,
                                                inventory.itemSizeId
                                        ),
                                        itemSize.size,
                                        inventory.amount
                                )
                        )
                ))
                .from(sell)
                .where(sell.id.eq(sellId))
                .join(sell.item, item)
                .join(item.brand, brand)
                .join(sell.inventories, inventory)
                .join(inventory.itemSize, itemSize)
                .fetchOne();
    }

    @Override
    public List<SellsDto> findAllByShop(Shop s) {
        return jpaQuery.select(
                        constructor(
                                SellsDto.class,
                                sell.discountPrice,
                                sell.discountRate,
                                item.name,
                                item.imageUrl,
                                brand.name,
                                list(
                                        constructor(
                                                SellInventoryDto.class,
                                                itemSize.size,
                                                inventory.amount
                                        )
                                )
                        )
                )
                .from(sell)
                .join(sell.item, item)
                .join(sell.shop, shop).where(shop.eq(s))
                .join(item.brand, brand)
                .join(sell.inventories, inventory)
                .join(inventory.itemSize, itemSize)
                .fetch();
    }

}
