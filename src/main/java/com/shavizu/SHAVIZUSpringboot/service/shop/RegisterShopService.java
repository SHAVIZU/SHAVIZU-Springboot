package com.shavizu.SHAVIZUSpringboot.service.shop;

import com.shavizu.SHAVIZUSpringboot.dto.request.RegisterShopRequest;
import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import com.shavizu.SHAVIZUSpringboot.entity.shop.repository.ShopRepository;
import com.shavizu.SHAVIZUSpringboot.entity.shop_information.ShopInformation;
import com.shavizu.SHAVIZUSpringboot.entity.shop_information.repository.ShopInformationRepository;
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
public class RegisterShopService {

    private final ShopRepository shopRepository;
    private final ShopInformationRepository shopInformationRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public TokenResponse execute(RegisterShopRequest request) {
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

        //회원가입할 때 샵 정보 엔티티도 생성
        shopInformationRepository.save(
                ShopInformation.createShopInformation(shop)
        );

        //토큰 반환
        return jwtProvider.generateToken(shop.getUserId());
    }

}
