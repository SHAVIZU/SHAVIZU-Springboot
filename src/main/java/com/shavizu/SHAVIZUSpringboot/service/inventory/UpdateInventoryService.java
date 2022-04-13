package com.shavizu.SHAVIZUSpringboot.service.inventory;

import com.shavizu.SHAVIZUSpringboot.dto.request.UpdateInventoryRequest;
import com.shavizu.SHAVIZUSpringboot.entity.inventory.Inventory;
import com.shavizu.SHAVIZUSpringboot.entity.inventory.InventoryId;
import com.shavizu.SHAVIZUSpringboot.entity.inventory.repository.InventoryRepository;
import com.shavizu.SHAVIZUSpringboot.entity.sell.repository.SellRepository;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.exception.NotFoundException;
import com.shavizu.SHAVIZUSpringboot.exception.UnAuthorizedException;
import com.shavizu.SHAVIZUSpringboot.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateInventoryService {

    private final AuthenticationFacade authenticationFacade;

    private final InventoryRepository inventoryRepository;
    private final SellRepository sellRepository;

    public void execute(UpdateInventoryRequest request) {
        Shop shop = authenticationFacade.getShop();

        sellRepository.findById(request.getInventories().get(0).getSellId())
                .filter(sell -> sell.getShop().equals(shop))
                .orElseThrow(() -> {
                    throw UnAuthorizedException.NOT_AUTHENTICATED;
                });

        for (UpdateInventoryRequest.InventoryDto i : request.getInventories()) {
            Inventory inventory = inventoryRepository.findById(new InventoryId(i.getSellId(), i.getItemSizeId()))
                    .orElseThrow(() -> {
                        throw NotFoundException.INVENTORY_NOT_FOUND;
                    });
            inventory.updateAmount(i.getAmount());
        }
    }

}
