package com.shavizu.SHAVIZUSpringboot.service.item;

import com.shavizu.SHAVIZUSpringboot.dto.response.StyleCodeSearchResponse;
import com.shavizu.SHAVIZUSpringboot.entity.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StyleCodeSearchService {

    private final ItemRepository itemRepository;

    public StyleCodeSearchResponse execute(String styleCode) {
        return itemRepository.findAllByStyleCode(styleCode);
    }

}
