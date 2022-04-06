package com.shavizu.SHAVIZUSpringboot.service.item;

import com.shavizu.SHAVIZUSpringboot.dto.request.AddItemRequest;
import com.shavizu.SHAVIZUSpringboot.dto.response.ItemStyleCodeResponse;
import com.shavizu.SHAVIZUSpringboot.entity.brand.Brand;
import com.shavizu.SHAVIZUSpringboot.entity.brand.repository.BrandRepository;
import com.shavizu.SHAVIZUSpringboot.entity.item.Category;
import com.shavizu.SHAVIZUSpringboot.entity.item.Item;
import com.shavizu.SHAVIZUSpringboot.entity.item.repository.ItemRepository;
import com.shavizu.SHAVIZUSpringboot.entity.item_size.ItemSize;
import com.shavizu.SHAVIZUSpringboot.entity.item_size.repository.ItemSizeRepository;
import com.shavizu.SHAVIZUSpringboot.exception.ConflictException;
import com.shavizu.SHAVIZUSpringboot.exception.NotFoundException;
import com.shavizu.SHAVIZUSpringboot.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AddItemService {

    private final BrandRepository brandRepository;
    private final ItemRepository itemRepository;
    private final ItemSizeRepository itemSizeRepository;

    private final AuthenticationFacade authenticationFacade;

    public ItemStyleCodeResponse addItem(AddItemRequest request) {
        authenticationFacade.getShop();
        isConflictStyleCode(request.getStyleCode());

        Brand brand = getBrand(request.getBrandId());

        Item item = saveItem(request.getName(), request.getStyleCode(), request.getCategory(), request.getImageUrl(), brand);

        saveItemSize(request.getIsFree(), item, request.getUnit());

        return new ItemStyleCodeResponse(item.getStyleCode());
    }

    private void isConflictStyleCode(String styleCode) {
        itemRepository.findByStyleCode(styleCode)
                .ifPresent(i -> {
                    throw ConflictException.ALREADY_EXISTS_STYLE_CODE;
                });
    }

    private Brand getBrand(long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> {
                    throw NotFoundException.BRAND_NOT_FOUND;
                });
    }

    private Item saveItem(String name, String styleCode, Category category, String imageUrl, Brand brand) {
        return itemRepository.save(
                Item.createItem(
                        name, styleCode, category, imageUrl, brand
                )
        );
    }

    private void saveItemSize(boolean isFree, Item item, AddItemRequest.Unit unit) {
        if (isFree) {
            itemSizeRepository.save(
                    ItemSize.createItemSize("free", item)
            );
        } else {
            for (int i = unit.getMin(); i <= unit.getMax(); i += unit.getSize()) {
                itemSizeRepository.save(ItemSize.createItemSize(String.valueOf(i), item));
            }
        }
    }


}