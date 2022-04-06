package com.shavizu.SHAVIZUSpringboot.service.shop;

import com.shavizu.SHAVIZUSpringboot.dto.request.UpdateShopInformationRequest;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.entity.shop_information.ShopInformation;
import com.shavizu.SHAVIZUSpringboot.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateShopInfoService {

    private final AuthenticationFacade authenticationFacade;

    public void execute(UpdateShopInformationRequest request) {
        Shop shop = authenticationFacade.getShop();
        ShopInformation shopInformation = shop.getShopInformation();

        shopInformation.updateShopInformation(
                request.getTelephone(),
                request.getAddress(),
                request.getDetailedAddress(),
                request.getOpeningHours(),
                request.getDescription(),
                request.getLatitude(),
                request.getLongitude()
        );
    }

}
