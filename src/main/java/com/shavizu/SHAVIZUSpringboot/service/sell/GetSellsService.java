package com.shavizu.SHAVIZUSpringboot.service.sell;

import com.shavizu.SHAVIZUSpringboot.dto.response.InventoryDto;
import com.shavizu.SHAVIZUSpringboot.dto.response.SellsDto;
import com.shavizu.SHAVIZUSpringboot.dto.response.SellsResponse;
import com.shavizu.SHAVIZUSpringboot.entity.inventory.repository.InventoryRepository;
import com.shavizu.SHAVIZUSpringboot.entity.sell.Sell;
import com.shavizu.SHAVIZUSpringboot.entity.sell.repository.SellRepository;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetSellsService {

    private final SellRepository sellRepository;
    private final InventoryRepository inventoryRepository;

    private final AuthenticationFacade authenticationFacade;

    public SellsResponse execute() {
        Shop shop = authenticationFacade.getShop();

        List<Sell> sells = sellRepository.findAllByShop(shop);

        return new SellsResponse(sells.stream().map(
                s -> new SellsDto(
                            s.getDiscountPrice(),
                            s.getDiscountRate(),
                            s.getItem().getName(),
                            s.getItem().getImageUrl(),
                            s.getItem().getBrand().getName(),
                            s.getInventories().stream().map(
                                    i -> new InventoryDto(
                                                new InventoryDto.InventoryId(
                                                        i.getSellId(),
                                                        i.getItemSizeId()
                                                ),
                                                i.getItemSize().getSize(),
                                                i.getAmount()
                                        )
                            ).collect(Collectors.toList())
                    )
        ).collect(Collectors.toList()));
    }

}
