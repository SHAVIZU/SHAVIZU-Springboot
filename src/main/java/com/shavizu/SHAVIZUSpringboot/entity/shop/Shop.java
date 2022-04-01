package com.shavizu.SHAVIZUSpringboot.entity.shop;

import com.shavizu.SHAVIZUSpringboot.entity.BaseIdCreatedAtEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Shop extends BaseIdCreatedAtEntity {

    @Column(length = 12, nullable = false, unique = true)
    private String userId;

    @Column(columnDefinition = "char(60)", nullable = false)
    private String password;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 12, nullable = false, unique = true)
    private String registrationNumber;

    @Column(length = 10, nullable = false)
    private String bossName;

    public static Shop createShop(String userId, String password, String name, String registrationNumber, String bossName) {
        Shop shop = new Shop();
        shop.userId = userId;
        shop.password = password;
        shop.name = name;
        shop.registrationNumber = registrationNumber;
        shop.bossName = bossName;

        return shop;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

}
