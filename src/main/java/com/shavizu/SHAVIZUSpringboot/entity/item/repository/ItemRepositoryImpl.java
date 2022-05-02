package com.shavizu.SHAVIZUSpringboot.entity.item.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shavizu.SHAVIZUSpringboot.dto.response.ItemDetailsResponse;
import com.shavizu.SHAVIZUSpringboot.dto.response.SizeDto;
import com.shavizu.SHAVIZUSpringboot.dto.response.StyleCodeSearchDto;
import com.shavizu.SHAVIZUSpringboot.dto.response.StyleCodeSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.core.types.Projections.constructor;
import static com.querydsl.core.types.Projections.list;
import static com.shavizu.SHAVIZUSpringboot.entity.brand.QBrand.brand;
import static com.shavizu.SHAVIZUSpringboot.entity.item.QItem.item;
import static com.shavizu.SHAVIZUSpringboot.entity.item_size.QItemSize.itemSize;

@RequiredArgsConstructor
@Repository
public class ItemRepositoryImpl implements ItemRepositoryExtension {

    private final JPAQueryFactory jpaQuery;

    @Override
    public StyleCodeSearchResponse findAllByStyleCode(String styleCode) {
        List<StyleCodeSearchDto> items =
                jpaQuery.select(
                        constructor(
                                StyleCodeSearchDto.class,
                                item.id,
                                item.name,
                                brand.name,
                                item.styleCode
                        )
                )
        .from(item)
        .where(item.styleCode.like(styleCode + "%"))
        .join(item.brand, brand)
        .fetch();

        return new StyleCodeSearchResponse(items);
    }

    @Override
    public ItemDetailsResponse findByItemId (Long id) {
        return (ItemDetailsResponse) jpaQuery.select(
                        constructor(
                                ItemDetailsResponse.class,
                                item.imageUrl,
                                list(
                                        constructor(
                                                SizeDto.class,
                                                itemSize.id,
                                                itemSize.size
                                        )
                                )
                        )
                )
                .from(item)
                .where(item.id.eq(id))
                .join(item.itemSizes, itemSize)
                .fetch();
    }


}
