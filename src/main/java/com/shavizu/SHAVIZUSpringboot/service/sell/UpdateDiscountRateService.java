package com.shavizu.SHAVIZUSpringboot.service.sell;

import com.shavizu.SHAVIZUSpringboot.dto.request.UpdateDiscountRateRequest;
import com.shavizu.SHAVIZUSpringboot.entity.sell.Sell;
import com.shavizu.SHAVIZUSpringboot.entity.sell.repository.SellRepository;
import com.shavizu.SHAVIZUSpringboot.exception.BadRequestException;
import com.shavizu.SHAVIZUSpringboot.exception.NotFoundException;
import com.shavizu.SHAVIZUSpringboot.exception.UnAuthorizedException;
import com.shavizu.SHAVIZUSpringboot.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateDiscountRateService {

    private final SellRepository sellRepository;

    private final AuthenticationFacade authenticationFacade;

    public void execute(long sellId, UpdateDiscountRateRequest request) {
        Sell sell = sellRepository.findById(sellId)
                .orElseThrow(() -> {
                    throw NotFoundException.SELL_NOT_FOUND;
                });
        if (!sell.getShop().equals(authenticationFacade.getShop())) {
            throw UnAuthorizedException.NOT_AUTHENTICATED;
        }
        
        int discountRate = request.getDiscountRate();

        if (discountRate < 0 || discountRate >= 100) {
            throw BadRequestException.EXCEPTION;
        }
        sell.updateDiscountRate(discountRate);
    }

}
