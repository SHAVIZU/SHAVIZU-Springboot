package com.shavizu.SHAVIZUSpringboot.entity.inventory;

import com.shavizu.SHAVIZUSpringboot.entity.item_size.ItemSize;
import com.shavizu.SHAVIZUSpringboot.entity.sell.Sell;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(InventoryId.class)
@Table(name = "tbl_inventory")
public class Inventory {

    @Id
    @Column(name = "sell_id")
    private Long sellId;

    @Id
    @Column(name = "item_size_id")
    private Long itemSizeId;

    @Column(nullable = false)
    private Long amount;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_id")
    private Sell sell;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_size_id")
    private ItemSize itemSize;

    public static Inventory createInventory(Sell sell, ItemSize itemSize, Long amount) {
        Inventory inventory = new Inventory();
        inventory.sell = sell;
        inventory.itemSize = itemSize;
        inventory.amount = amount;

        return inventory;
    }

    public void updateAmount(long amount) {
        this.amount = amount;
    }

}
