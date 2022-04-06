package com.shavizu.SHAVIZUSpringboot.controller;

import com.shavizu.SHAVIZUSpringboot.dto.request.AddItemRequest;
import com.shavizu.SHAVIZUSpringboot.dto.response.ItemStyleCodeResponse;
import com.shavizu.SHAVIZUSpringboot.service.item.AddItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/items")
@RestController
public class ItemController {

    private final AddItemService addItemService;

    @PostMapping
    public ItemStyleCodeResponse addItem(@RequestBody @Valid AddItemRequest request) {
        return addItemService.addItem(request);
    }

}
