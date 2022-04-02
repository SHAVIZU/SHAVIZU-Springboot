package com.shavizu.SHAVIZUSpringboot.entity.inventory.repository;

import com.shavizu.SHAVIZUSpringboot.entity.inventory.Inventory;
import com.shavizu.SHAVIZUSpringboot.entity.inventory.InventoryId;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<Inventory, InventoryId> {
}
