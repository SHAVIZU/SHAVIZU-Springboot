package com.shavizu.SHAVIZUSpringboot.entity.inventory;

import com.shavizu.SHAVIZUSpringboot.entity.item.Item;
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
    private Long sellId;

    @Id
    private Long itemId;

    @Column(nullable = false)
    private Long amount;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_id")
    private Sell sell;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public static Inventory createInventory(Sell sell, Item item, Long amount) {
        Inventory inventory = new Inventory();
        inventory.sell = sell;
        inventory.item = item;
        inventory.amount = amount;

        return inventory;
    }

}
