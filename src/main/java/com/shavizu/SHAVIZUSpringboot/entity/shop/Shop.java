package com.shavizu.SHAVIZUSpringboot.entity.shop;

import com.shavizu.SHAVIZUSpringboot.entity.BaseIdCreatedAtEntity;
import com.shavizu.SHAVIZUSpringboot.entity.shop_image.ShopImage;
import com.shavizu.SHAVIZUSpringboot.entity.shop_information.ShopInformation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_shop")
public class Shop extends BaseIdCreatedAtEntity {

    @Column(length = 12, nullable = false, unique = true)
    private String userId;

    @Column(columnDefinition = "char(60)", nullable = false)
    private String password;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(columnDefinition = "char(12)", nullable = false, unique = true)
    private String registrationNumber;

    @Column(length = 10, nullable = false)
    private String bossName;

    @Column(nullable = false)
    private LocalDate openingDate;

    @OneToOne(mappedBy = "shop", cascade = CascadeType.ALL)
    private ShopInformation shopInformation;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<ShopImage> shopImages = new ArrayList<>();

    public static Shop createShop(String userId, String password, String name, String registrationNumber, String bossName, LocalDate openingDate) {
        Shop shop = new Shop();
        shop.userId = userId;
        shop.password = password;
        shop.name = name;
        shop.registrationNumber = registrationNumber;
        shop.bossName = bossName;
        shop.openingDate = openingDate;

        return shop;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

}
