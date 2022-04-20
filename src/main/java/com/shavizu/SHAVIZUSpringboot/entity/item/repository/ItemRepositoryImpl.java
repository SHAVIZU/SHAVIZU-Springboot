package com.shavizu.SHAVIZUSpringboot.entity.item.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shavizu.SHAVIZUSpringboot.dto.response.StyleCodeSearchDto;
import com.shavizu.SHAVIZUSpringboot.dto.response.StyleCodeSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.core.types.Projections.constructor;
import static com.shavizu.SHAVIZUSpringboot.entity.brand.QBrand.brand;
import static com.shavizu.SHAVIZUSpringboot.entity.item.QItem.item;

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
                                item.name,
                                brand.name,
                                item.styleCode
                        )
                )
        .from(item)
        .where(item.styleCode.like(styleCode))
        .join(item.brand, brand)
        .fetch();

        return new StyleCodeSearchResponse(items);
    }
}
