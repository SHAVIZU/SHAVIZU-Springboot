package com.shavizu.SHAVIZUSpringboot.service.shop;

import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.entity.shop_image.ShopImage;
import com.shavizu.SHAVIZUSpringboot.entity.shop_image.repository.ShopImageRepository;
import com.shavizu.SHAVIZUSpringboot.exception.BadRequestException;
import com.shavizu.SHAVIZUSpringboot.facade.AmazonS3Facade;
import com.shavizu.SHAVIZUSpringboot.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UploadShopImageService {

    private final ShopImageRepository shopImageRepository;

    private final AmazonS3Facade amazonS3Facade;
    private final AuthenticationFacade authenticationFacade;

    public void execute(List<MultipartFile> files) {
        Shop shop = authenticationFacade.getShop();
        deleteShopImages(shop);

        for (int i = 0; i < files.size(); i++) {
            String imageUrl = uploadImage(shop, files.get(i));
            shopImageRepository.save(
                    ShopImage.createShopImage(
                            imageUrl,
                            i+1,
                            shop
                    )
            );
        }
    }

    private String uploadImage(Shop shop, MultipartFile file) {
        try {
            return amazonS3Facade.uploadImage(file);
        } catch(IOException e) {
            deleteShopImages(shop);
            throw BadRequestException.FILE_SAVE_FAILED_EXCEPTION;
        }
    }

    private void deleteShopImages(Shop shop) {
        shopImageRepository.findAllByShop(shop).forEach(
                i -> {
                    amazonS3Facade.delete(i.getImageUrl());
                    shopImageRepository.delete(i);
                }
        );
    }

}
