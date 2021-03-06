package com.shavizu.SHAVIZUSpringboot.controller;

import com.shavizu.SHAVIZUSpringboot.dto.request.RegisterShopRequest;
import com.shavizu.SHAVIZUSpringboot.dto.request.UpdateShopInformationRequest;
import com.shavizu.SHAVIZUSpringboot.dto.request.UpdatePasswordRequest;
import com.shavizu.SHAVIZUSpringboot.security.jwt.TokenResponse;
import com.shavizu.SHAVIZUSpringboot.service.shop.DeleteShopService;
import com.shavizu.SHAVIZUSpringboot.service.shop.RegisterShopService;
import com.shavizu.SHAVIZUSpringboot.service.shop.UpdateShopInfoService;
import com.shavizu.SHAVIZUSpringboot.service.shop.UpdatePasswordService;
import com.shavizu.SHAVIZUSpringboot.service.shop.UploadShopImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/shops")
@RestController
public class ShopController {

    private final RegisterShopService registerShopService;
    private final DeleteShopService deleteShopService;
    private final UpdatePasswordService updatePasswordService;
    private final UpdateShopInfoService updateShopInfoService;
    private final UploadShopImageService uploadShopImageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TokenResponse registerShop(@RequestBody @Valid RegisterShopRequest request) {
        return registerShopService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteShop() {
        deleteShopService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        updatePasswordService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/info")
    public void updateShopInfo(@RequestBody @Valid UpdateShopInformationRequest request) {
        updateShopInfoService.execute(request);
    }

    @PostMapping("/image")
    public void uploadImage(@RequestParam List<MultipartFile> files) {
        uploadShopImageService.execute(files);
    }

}
