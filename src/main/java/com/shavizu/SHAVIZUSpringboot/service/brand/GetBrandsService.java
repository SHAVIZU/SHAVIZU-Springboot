package com.shavizu.SHAVIZUSpringboot.service.brand;

import com.shavizu.SHAVIZUSpringboot.dto.response.BrandsResponse;
import com.shavizu.SHAVIZUSpringboot.entity.brand.Brand;
import com.shavizu.SHAVIZUSpringboot.entity.brand.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetBrandsService {

    private final BrandRepository brandRepository;

    public BrandsResponse execute(String keyword) {
        List<Brand> brands = brandRepository.findTop5ByNameLikeOrderByName(keyword);
        return new BrandsResponse(
                brands.stream().map(
                        b -> BrandsResponse.BrandResponse.createBrandResponse(b.getId(), b.getName())
                ).collect(Collectors.toList())
        );
    }

}
