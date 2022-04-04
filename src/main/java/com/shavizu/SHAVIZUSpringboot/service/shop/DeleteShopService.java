package com.shavizu.SHAVIZUSpringboot.service.shop;

import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.entity.shop.repository.ShopRepository;
import com.shavizu.SHAVIZUSpringboot.entity.shop_image.ShopImage;
import com.shavizu.SHAVIZUSpringboot.facade.AmazonS3Facade;
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
    private final AmazonS3Facade amazonS3Facade;

    public void execute() {
        Shop shop = authenticationFacade.getShop();

        //샵 이미지 객체 삭제하기
        if (shop.getShopImages().size() != 0) {
            for (ShopImage shopImage : shop.getShopImages()) {
                amazonS3Facade.delete(shopImage.getImageUrl());
            }
        }

        shopRepository.delete(shop);
    }

}
