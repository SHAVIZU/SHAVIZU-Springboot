package com.shavizu.SHAVIZUSpringboot.controller;

import com.shavizu.SHAVIZUSpringboot.dto.request.AddBrandRequest;
import com.shavizu.SHAVIZUSpringboot.service.brand.AddBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/brands")
@RestController
public class BrandController {

    private final AddBrandService addBrandService;

    public void addBrand(@RequestBody @Valid AddBrandRequest request) {
        addBrandService.execute(request);
    }

}
