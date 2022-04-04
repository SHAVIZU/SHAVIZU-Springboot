package com.shavizu.SHAVIZUSpringboot.service.shop;

import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.entity.shop.repository.ShopRepository;
import com.shavizu.SHAVIZUSpringboot.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DeleteShopService {

    private final ShopRepository shopRepository;

    private final AuthenticationFacade authenticationFacade;

    public void execute() {
        Shop shop = authenticationFacade.getShop();
        shopRepository.delete(shop);
    }

}
