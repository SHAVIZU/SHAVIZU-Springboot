package com.shavizu.SHAVIZUSpringboot.service.item;

import com.shavizu.SHAVIZUSpringboot.dto.response.ItemDetailsResponse;
import com.shavizu.SHAVIZUSpringboot.entity.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetItemDetailsService {

    private final ItemRepository itemRepository;

    public ItemDetailsResponse execute(Long id) {
        return itemRepository.findByItemId(id);
    }

}
