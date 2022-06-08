package com.shavizu.SHAVIZUSpringboot.service.sell;

import com.shavizu.SHAVIZUSpringboot.dto.response.SellsDto;
import com.shavizu.SHAVIZUSpringboot.dto.response.SellsResponse;
import com.shavizu.SHAVIZUSpringboot.entity.sell.repository.SellRepository;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetSellsService {

    private final SellRepository sellRepository;

    private final AuthenticationFacade authenticationFacade;

    public SellsResponse execute() {
        Shop shop = authenticationFacade.getShop();

        List<SellsDto> sells = sellRepository.findAllByShop(shop);
        return new SellsResponse(sells);
    }

}
