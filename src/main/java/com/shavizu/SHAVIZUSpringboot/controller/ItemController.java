package com.shavizu.SHAVIZUSpringboot.controller;

import com.shavizu.SHAVIZUSpringboot.dto.request.AddItemRequest;
import com.shavizu.SHAVIZUSpringboot.dto.response.ItemDetailsResponse;
import com.shavizu.SHAVIZUSpringboot.dto.response.ItemStyleCodeResponse;
import com.shavizu.SHAVIZUSpringboot.dto.response.StyleCodeSearchResponse;
import com.shavizu.SHAVIZUSpringboot.service.item.AddItemService;
import com.shavizu.SHAVIZUSpringboot.service.item.GetItemDetailsService;
import com.shavizu.SHAVIZUSpringboot.service.item.StyleCodeSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/items")
@RestController
public class ItemController {

    private final AddItemService addItemService;
    private final StyleCodeSearchService styleCodeSearchService;
    private final GetItemDetailsService getItemDetailsService;

    @PostMapping
    public ItemStyleCodeResponse addItem(@RequestBody @Valid AddItemRequest request) {
        return addItemService.addItem(request);
    }

    @GetMapping
    public StyleCodeSearchResponse styleCodeSearch(@RequestParam("style_code") String styleCode) {
        return styleCodeSearchService.execute(styleCode);
    }
    
    @GetMapping("/details/{item_id}")
    public ItemDetailsResponse getItemDetails(@PathVariable("item_id") Long id) {
        return getItemDetailsService.execute(id);
    }

}
