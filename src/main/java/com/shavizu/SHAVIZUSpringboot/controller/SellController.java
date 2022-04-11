package com.shavizu.SHAVIZUSpringboot.controller;

import com.shavizu.SHAVIZUSpringboot.dto.response.SellDetailsResponse;
import com.shavizu.SHAVIZUSpringboot.service.sell.SellDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/sells")
@RestController
public class SellController {

    private final SellDetailsService sellDetailsService;

    @GetMapping("/{sell_id}")
    public SellDetailsResponse getSellDetails(@PathVariable("sell_id") long sellId) {
        return sellDetailsService.execute(sellId);
    }

}
