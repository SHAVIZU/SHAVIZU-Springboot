package com.shavizu.SHAVIZUSpringboot.controller;

import com.shavizu.SHAVIZUSpringboot.dto.request.AddSellRequest;
import com.shavizu.SHAVIZUSpringboot.dto.request.UpdateDiscountRateRequest;
import com.shavizu.SHAVIZUSpringboot.dto.request.UpdateInventoryRequest;
import com.shavizu.SHAVIZUSpringboot.dto.response.SellDetailsResponse;
import com.shavizu.SHAVIZUSpringboot.dto.response.SellsResponse;
import com.shavizu.SHAVIZUSpringboot.service.inventory.UpdateInventoryService;
import com.shavizu.SHAVIZUSpringboot.service.sell.DeleteSellService;
import com.shavizu.SHAVIZUSpringboot.service.sell.GetSellsService;
import com.shavizu.SHAVIZUSpringboot.service.sell.UpdateDiscountRateService;
import com.shavizu.SHAVIZUSpringboot.service.sell.AddSellService;
import com.shavizu.SHAVIZUSpringboot.service.sell.GetSellDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/sells")
@RestController
public class SellController {

    private final GetSellDetailsService getSellDetailsService;
    private final UpdateInventoryService updateInventoryService;
    private final UpdateDiscountRateService updateDiscountRateService;
    private final AddSellService addSellService;
    private final DeleteSellService deleteSellService;
    private final GetSellsService getSellsService;

    @GetMapping("/details/{sell_id}")
    public SellDetailsResponse getSellDetails(@PathVariable("sell_id") long sellId) {
        return getSellDetailsService.execute(sellId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/inventory/{sell_id}")
    public void updateInventory(@PathVariable("sell_id") Long sellId, @RequestBody @Valid UpdateInventoryRequest request) {
        updateInventoryService.execute(sellId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/discount/{sell_id}")
    public void updateDiscountRate(@PathVariable("sell_id") Long sellId, @RequestBody @Valid UpdateDiscountRateRequest request) {
        updateDiscountRateService.execute(sellId, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addSell(@RequestParam("item_id") Long itemId, @RequestBody @Valid AddSellRequest request) {
        addSellService.execute(itemId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{sell_id}")
    public void deleteSell(@PathVariable("sell_id") Long sellId) {
        deleteSellService.execute(sellId);
    }

    @GetMapping
    public SellsResponse getSells() {
        return getSellsService.execute();
    }

}
