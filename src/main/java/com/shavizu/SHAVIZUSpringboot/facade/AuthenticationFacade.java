package com.shavizu.SHAVIZUSpringboot.facade;

import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.entity.shop.repository.ShopRepository;
import com.shavizu.SHAVIZUSpringboot.exception.NotFoundException;
import com.shavizu.SHAVIZUSpringboot.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationFacade {

    private final ShopRepository shopRepository;

    public Shop getShop() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw UnAuthorizedException.NOT_AUTHENTICATED;
        }

        return shopRepository.findByUserId(authentication.getName())
                .orElseThrow(() -> {
                    throw NotFoundException.USER_NAME_NOT_FOUND;
                });
    }

}
