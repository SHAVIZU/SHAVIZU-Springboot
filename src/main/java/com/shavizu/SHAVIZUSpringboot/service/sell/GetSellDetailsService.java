package com.shavizu.SHAVIZUSpringboot.service.sell;

import com.shavizu.SHAVIZUSpringboot.dto.response.SellDetailsResponse;
import com.shavizu.SHAVIZUSpringboot.entity.sell.repository.SellRepository;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.exception.NotFoundException;
import com.shavizu.SHAVIZUSpringboot.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        return sellRepository.findBySellId(sellId);
    }

}
