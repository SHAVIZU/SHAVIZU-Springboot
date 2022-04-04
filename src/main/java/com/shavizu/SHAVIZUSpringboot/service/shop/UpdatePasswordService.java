package com.shavizu.SHAVIZUSpringboot.service.shop;

import com.shavizu.SHAVIZUSpringboot.dto.request.UpdatePasswordRequest;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.entity.shop.repository.ShopRepository;
import com.shavizu.SHAVIZUSpringboot.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdatePasswordService {

    private final ShopRepository shopRepository;

    private final PasswordEncoder passwordEncoder;

    public void execute(UpdatePasswordRequest request) {
        Shop shop = shopRepository.findByUserId(request.getUserId())
                .filter(s -> s.getRegistrationNumber().equals(request.getRegistrationNumber()))
                .orElseThrow(() -> NotFoundException.USER_NAME_NOT_FOUND);

        String newPassword = passwordEncoder.encode(request.getNewPassword());
        shop.updatePassword(newPassword);
    }

}
