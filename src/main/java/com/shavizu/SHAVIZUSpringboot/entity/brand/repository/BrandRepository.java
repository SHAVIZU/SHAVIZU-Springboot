package com.shavizu.SHAVIZUSpringboot.entity.brand.repository;

import com.shavizu.SHAVIZUSpringboot.entity.brand.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends CrudRepository<Brand, Long> {
    Optional<Brand> findByName(String name);
    List<Brand> findTop5ByNameLikeOrderByName(String keyword);
}
