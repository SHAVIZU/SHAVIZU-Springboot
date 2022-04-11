package com.shavizu.SHAVIZUSpringboot.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class BrandsResponse {

    private final List<BrandResponse> brands;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class BrandResponse {
        private long id;
        private String name;

        public static BrandResponse createBrandResponse(long id, String name) {
            BrandResponse brandResponse = new BrandResponse();
            brandResponse.id = id;
            brandResponse.name = name;

            return brandResponse;
        }
    }

}
