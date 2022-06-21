package com.shavizu.SHAVIZUSpringboot.service.sell;

import com.shavizu.SHAVIZUSpringboot.dto.response.InventoryDto;
import com.shavizu.SHAVIZUSpringboot.dto.response.ItemDto;
import com.shavizu.SHAVIZUSpringboot.dto.response.SellDetailsResponse;
import com.shavizu.SHAVIZUSpringboot.dto.response.SellDto;
import com.shavizu.SHAVIZUSpringboot.entity.inventory.Inventory;
import com.shavizu.SHAVIZUSpringboot.entity.item.Item;
import com.shavizu.SHAVIZUSpringboot.entity.sell.Sell;
import com.shavizu.SHAVIZUSpringboot.entity.sell.repository.SellRepository;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.exception.NotFoundException;
import com.shavizu.SHAVIZUSpringboot.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetSellDetailsService {

    private final SellRepository sellRepository;

    private final AuthenticationFacade authenticationFacade;

    public SellDetailsResponse execute(Long sellId) {
        Shop shop = authenticationFacade.getShop();
        sellRepository.findByIdAndShop(sellId, shop)
                .orElseThrow(() -> {
                    throw NotFoundException.SELL_NOT_FOUND;
                });

        Sell sell = sellRepository.findBySellId(shop, sellId);
        Item item = sell.getItem();
        List<Inventory> inventories = sell.getInventories();
        return new SellDetailsResponse(
                new SellDto(
                        sell.getPrice(),
                        sell.getDiscountRate(),
                        sell.getDiscountPrice()
                ),
                new ItemDto(
                        item.getImageUrl(),
                        item.getBrand().getName(),
                        item.getName()
                ),
                inventories.stream().map(
                        i -> new InventoryDto(
                                i.getItemSizeId(),
                                i.getItemSize().getSize(),
                                i.getAmount()
                        )
                ).collect(Collectors.toList())
        );
    }

}
