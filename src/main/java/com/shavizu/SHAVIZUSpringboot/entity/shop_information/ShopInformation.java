package com.shavizu.SHAVIZUSpringboot.entity.shop_information;

import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Random;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_shop_information")
public class ShopInformation {

    @Id
    private Long shopId;

    @Column(length = 11, nullable = false, unique = true)
    private String telephone;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 50, nullable = false)
    private String detailedAddress;

    @Column(length = 200, nullable = false)
    private String openingHours;

    @Column(length = 150)
    private String description;

    @Column(columnDefinition = "decimal(10,8)", nullable = false)
    private BigDecimal latitude;

    @Column(columnDefinition = "decimal(11,8)", nullable = false)
    private BigDecimal longitude;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    public static ShopInformation createShopInformation(Shop shop) {
        ShopInformation shopInformation = new ShopInformation();
        shopInformation.shop = shop;
        shopInformation.telephone = String.valueOf(Math.random());
        shopInformation.address = "";
        shopInformation.detailedAddress = "";
        shopInformation.openingHours = "";
        shopInformation.description = null;
        shopInformation.latitude = BigDecimal.ZERO;
        shopInformation.longitude = BigDecimal.ZERO;
        return shopInformation;
    }

    public void updateShopInformation(String telephone, String address,
                                      String detailedAddress, String openingHours,
                                      String description, BigDecimal latitude, BigDecimal longitude) {
        this.telephone = telephone;
        this.address = address;
        this.detailedAddress = detailedAddress;
        this.openingHours = openingHours;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;

    }

}
