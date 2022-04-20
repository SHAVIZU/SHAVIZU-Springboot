package com.shavizu.SHAVIZUSpringboot.entity.item.repository;

import com.shavizu.SHAVIZUSpringboot.entity.item.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long>, ItemRepositoryExtension {
    Optional<Item> findByStyleCode(String styleCode);
}
