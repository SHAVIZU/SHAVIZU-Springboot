package com.shavizu.SHAVIZUSpringboot.dto.request;

import com.shavizu.SHAVIZUSpringboot.entity.item.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddItemRequest {

    @NotBlank
    @Length(max = 20)
    private String styleCode;

    @NotBlank
    @Length(max = 50)
    private String name;

    @NotNull
    private Long brandId;

    @NotNull
    private Category category;

    @NotBlank
    private String imageUrl;

    @NotNull
    private Boolean isFree;

    private Unit unit;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Unit {
        private int min;
        private int max;
        private int size;
    }

}
