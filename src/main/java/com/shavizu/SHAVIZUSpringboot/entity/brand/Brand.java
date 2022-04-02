package com.shavizu.SHAVIZUSpringboot.entity.brand;

import com.shavizu.SHAVIZUSpringboot.entity.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_brand")
public class Brand extends BaseIdEntity {

    @Column(length = 30, nullable = false, unique = true)
    private String name;

    public static Brand createBrand(String name) {
        Brand brand = new Brand();
        brand.name = name;

        return brand;
    }

}
