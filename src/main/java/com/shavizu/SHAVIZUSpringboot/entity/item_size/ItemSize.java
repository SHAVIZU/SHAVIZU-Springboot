package com.shavizu.SHAVIZUSpringboot.entity.item_size;

import com.shavizu.SHAVIZUSpringboot.entity.BaseIdEntity;
import com.shavizu.SHAVIZUSpringboot.entity.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_item_size")
public class ItemSize extends BaseIdEntity {

    @Column(length = 10, nullable = false)
    private String size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    public static ItemSize createItemSize(String size, Item item) {
        ItemSize itemSize = new ItemSize();
        itemSize.size = size;
        itemSize.item = item;

        return itemSize;
    }

}
