package com.shavizu.SHAVIZUSpringboot.entity.item;

import com.shavizu.SHAVIZUSpringboot.entity.BaseIdEntity;
import com.shavizu.SHAVIZUSpringboot.entity.brand.Brand;
import com.shavizu.SHAVIZUSpringboot.entity.item_size.ItemSize;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_item")
public class Item extends BaseIdEntity {

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 20, nullable = false, unique = true)
    private String styleCode;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Category category;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;


    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemSize> itemSizes = new ArrayList<>();
    public static Item createItem(String name, String styleCode, Category category, String imageUrl, Brand brand) {
        Item item = new Item();
        item.name = name;
        item.styleCode = styleCode;
        item.category = category;
        item.imageUrl = imageUrl;
        item.brand = brand;

        return item;
    }

}
