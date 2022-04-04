package com.shavizu.SHAVIZUSpringboot.service.auth;

import com.shavizu.SHAVIZUSpringboot.dto.request.LoginRequest;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.entity.shop.repository.ShopRepository;
import com.shavizu.SHAVIZUSpringboot.exception.NotFoundException;
import com.shavizu.SHAVIZUSpringboot.security.jwt.JwtProvider;
import com.shavizu.SHAVIZUSpringboot.security.jwt.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class LoginService {

    private final ShopRepository shopRepository;

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse execute(LoginRequest request) {
        Shop shop = shopRepository.findByUserId(request.getUserId())
                .filter(s -> passwordEncoder.matches(request.getPassword(), s.getPassword()))
                .orElseThrow(() -> {
                    throw NotFoundException.USER_NAME_NOT_FOUND;
                });

        return jwtProvider.generateToken(shop.getUserId());
    }

}
