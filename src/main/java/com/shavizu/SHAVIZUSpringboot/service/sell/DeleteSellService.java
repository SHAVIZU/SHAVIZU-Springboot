package com.shavizu.SHAVIZUSpringboot.service.sell;

import com.shavizu.SHAVIZUSpringboot.entity.sell.repository.SellRepository;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteSellService {

    private final SellRepository sellRepository;

    private final AuthenticationFacade authenticationFacade;

    public void execute(long sellId) {
        Shop shop = authenticationFacade.getShop();
        sellRepository.findById(sellId)
                .ifPresent(sellRepository::delete);
    }

}
