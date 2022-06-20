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
import javax.persistence.UniqueConstraint;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(InventoryId.class)
@Table(name = "tbl_inventory")
public class Inventory {

    @Id
    @Column(name = "sell_id", nullable = false)
    private Long sellId;

    @Id
    @Column(name = "item_size_id", nullable = false)
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

    public static Inventory createInventory(Long sellId, Long itemSizeId, Long amount) {
        Inventory inventory = new Inventory();
        inventory.sellId = sellId;
        inventory.itemSizeId = itemSizeId;
        inventory.amount = amount;

        return inventory;
    }

    public void updateAmount(long amount) {
        this.amount = amount;
    }

}
