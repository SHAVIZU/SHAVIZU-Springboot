package com.shavizu.SHAVIZUSpringboot.service.sell;

import com.shavizu.SHAVIZUSpringboot.dto.request.AddSellRequest;
import com.shavizu.SHAVIZUSpringboot.entity.inventory.Inventory;
import com.shavizu.SHAVIZUSpringboot.entity.inventory.repository.InventoryRepository;
import com.shavizu.SHAVIZUSpringboot.entity.item.Item;
import com.shavizu.SHAVIZUSpringboot.entity.item.repository.ItemRepository;
import com.shavizu.SHAVIZUSpringboot.entity.item_size.ItemSize;
import com.shavizu.SHAVIZUSpringboot.entity.item_size.repository.ItemSizeRepository;
import com.shavizu.SHAVIZUSpringboot.entity.sell.Sell;
import com.shavizu.SHAVIZUSpringboot.entity.sell.repository.SellRepository;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.exception.NotFoundException;
import com.shavizu.SHAVIZUSpringboot.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AddSellService {

    private final ItemRepository itemRepository;
    private final SellRepository sellRepository;
    private final ItemSizeRepository itemSizeRepository;
    private final InventoryRepository inventoryRepository;

    private final AuthenticationFacade authenticationFacade;

    public void execute(Long itemId, AddSellRequest request) {
        //판매 정보 저장
        Shop shop = authenticationFacade.getShop();
        Item item = getItem(itemId);

        Sell sell = sellRepository.save(
                Sell.createSell(
                        request.getSell().getPrice(),
                        request.getSell().getDiscountRate(),
                        shop,
                        item
                )
        );

        //재고량 저장
        for (AddSellRequest.ItemDto i : request.getItem()) {
            ItemSize itemSize = getItemSize(i.getItemSizeId());

            inventoryRepository.save(
                    Inventory.createInventory(
                            sell,
                            itemSize,
                            i.getAmount()
                    )
            );
        }
    }

    private Item getItem(long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> {
                    throw NotFoundException.ITEM_NOT_FOUND;
                });
    }

    private ItemSize getItemSize(long itemSizeId) {
        return itemSizeRepository.findById(itemSizeId)
                .orElseThrow(() -> {
                    throw NotFoundException.ITEM_SIZE_NOT_FOUND;
                });
    }

}
