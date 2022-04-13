package com.shavizu.SHAVIZUSpringboot.controller;

import com.shavizu.SHAVIZUSpringboot.dto.request.UpdateInventoryRequest;
import com.shavizu.SHAVIZUSpringboot.dto.response.SellDetailsResponse;
import com.shavizu.SHAVIZUSpringboot.service.inventory.UpdateInventoryService;
import com.shavizu.SHAVIZUSpringboot.service.sell.GetSellDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/sells")
@RestController
public class SellController {

    private final GetSellDetailsService getSellDetailsService;
    private final UpdateInventoryService updateInventoryService;

    @GetMapping("/details/{sell_id}")
    public SellDetailsResponse getSellDetails(@PathVariable("sell_id") long sellId) {
        return getSellDetailsService.execute(sellId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/inventory/{sell_id}")
    public void updateInventory(@PathVariable("sell_id") Long sellId, @RequestBody @Valid UpdateInventoryRequest request) {
        updateInventoryService.execute(sellId, request);
    }

}
