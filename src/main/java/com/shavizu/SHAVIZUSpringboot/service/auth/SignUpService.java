package com.shavizu.SHAVIZUSpringboot.service.auth;

import com.shavizu.SHAVIZUSpringboot.dto.request.SignUpRequest;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.entity.shop.repository.ShopRepository;
import com.shavizu.SHAVIZUSpringboot.exception.ConflictException;
import com.shavizu.SHAVIZUSpringboot.security.jwt.JwtProvider;
import com.shavizu.SHAVIZUSpringboot.security.jwt.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class SignUpService {

    private final ShopRepository shopRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public TokenResponse execute(SignUpRequest request) {
        //로그인할 때 사용하는 아이디, 사업자 등록번호는 unique해야 한다.
        shopRepository.findByUserIdOrRegistrationNumber(request.getUserId(), request.getRegistrationNumber())
                .ifPresent(s -> {
                    throw ConflictException.ALREADY_EXISTS_SHOP;
                });

        //회원정보 저장
        Shop shop = shopRepository.save(
                Shop.createShop(
                        request.getUserId(),
                        passwordEncoder.encode(request.getPassword()),
                        request.getName(),
                        request.getRegistrationNumber(),
                        request.getBossName()
                )
        );

        //토큰 반환
        return jwtProvider.generateToken(shop.getUserId());
    }

}
