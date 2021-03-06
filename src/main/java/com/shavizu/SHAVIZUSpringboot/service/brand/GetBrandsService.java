package com.shavizu.SHAVIZUSpringboot.service.brand;

import com.shavizu.SHAVIZUSpringboot.dto.response.BrandDto;
import com.shavizu.SHAVIZUSpringboot.dto.response.BrandListResponse;
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

    public BrandListResponse execute(String keyword) {
        List<Brand> brands = brandRepository.findTop5ByNameLikeOrderByName("%" + keyword + "%");
        return new BrandListResponse(
                brands.stream().map(
                        b -> BrandDto.builder()
                                .id(b.getId())
                                .name(b.getName())
                                .build()
                ).collect(Collectors.toList())
        );
    }

}
