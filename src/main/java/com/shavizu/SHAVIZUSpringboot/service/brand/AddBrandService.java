package com.shavizu.SHAVIZUSpringboot.service.brand;

import com.shavizu.SHAVIZUSpringboot.dto.request.AddBrandRequest;
import com.shavizu.SHAVIZUSpringboot.entity.brand.Brand;
import com.shavizu.SHAVIZUSpringboot.entity.brand.repository.BrandRepository;
import com.shavizu.SHAVIZUSpringboot.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AddBrandService {

    private final BrandRepository brandRepository;

    private final AuthenticationFacade authenticationFacade;

    public void execute(AddBrandRequest request) {
        authenticationFacade.getShop();

        if(brandRepository.findByName(request.getName()).isEmpty()) {
            brandRepository.save(
                    Brand.createBrand(request.getName())
            );
        }
    }

}
