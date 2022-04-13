package com.shavizu.SHAVIZUSpringboot.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateDiscountRateRequest {

    @NotNull
    private Integer discountRate;

}
