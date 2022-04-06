package com.shavizu.SHAVIZUSpringboot.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddBrandRequest {

    @NotBlank
    @Length(max = 30)
    private String name;

}
