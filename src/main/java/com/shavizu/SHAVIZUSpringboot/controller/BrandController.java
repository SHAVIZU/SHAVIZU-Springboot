package com.shavizu.SHAVIZUSpringboot.controller;

import com.shavizu.SHAVIZUSpringboot.dto.request.AddBrandRequest;
import com.shavizu.SHAVIZUSpringboot.dto.response.GetBrandsResponse;
import com.shavizu.SHAVIZUSpringboot.service.brand.AddBrandService;
import com.shavizu.SHAVIZUSpringboot.service.brand.GetBrandsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/brands")
@RestController
public class BrandController {

    private final AddBrandService addBrandService;
    private final GetBrandsService getBrandsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addBrand(@RequestBody @Valid AddBrandRequest request) {
        addBrandService.execute(request);
    }

    @GetMapping
    public GetBrandsResponse getBrands(@RequestParam String keyword) {
        return getBrandsService.execute(keyword);
    }

}
