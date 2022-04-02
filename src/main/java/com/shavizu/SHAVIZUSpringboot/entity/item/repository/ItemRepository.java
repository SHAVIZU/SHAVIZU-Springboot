package com.shavizu.SHAVIZUSpringboot.entity.item.repository;

import com.shavizu.SHAVIZUSpringboot.entity.item.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
