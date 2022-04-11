package com.shavizu.SHAVIZUSpringboot.entity.inventory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InventoryId implements Serializable {

    private Long sellId;

    private Long itemSizeId;

}
