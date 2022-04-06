package com.shavizu.SHAVIZUSpringboot.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateShopInformationRequest {

    @NotBlank
    @Length(min = 9, max = 11)
    private String telephone;

    @NotBlank
    @Length(max = 100)
    private String address;

    @NotBlank
    @Length(max = 50)
    private String detailedAddress;

    @NotBlank
    @Length(max = 200)
    private String openingHours;

    @Length(max = 150)
    private String description;

    @NotNull
    private BigDecimal latitude;

    @NotNull
    private BigDecimal longitude;

}
