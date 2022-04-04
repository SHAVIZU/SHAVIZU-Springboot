package com.shavizu.SHAVIZUSpringboot.entity.shop_information;

import com.shavizu.SHAVIZUSpringboot.entity.shop.Shop;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

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
    @OneToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    public static ShopInformation createShopInformation(Shop shop, String telephone, String address, String detailedAddress, String openingHours, String description, BigDecimal latitude, BigDecimal longitude) {
        ShopInformation shopInformation = new ShopInformation();
        shopInformation.shop = shop;
        shopInformation.telephone = telephone;
        shopInformation.address = address;
        shopInformation.detailedAddress = detailedAddress;
        shopInformation.openingHours = openingHours;
        shopInformation.description = description;
        shopInformation.latitude = latitude;
        shopInformation.longitude = longitude;

        return shopInformation;
    }

}
