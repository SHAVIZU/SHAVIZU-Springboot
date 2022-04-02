package com.shavizu.SHAVIZUSpringboot.security.auth;

import com.shavizu.SHAVIZUSpringboot.entity.shop.repository.ShopRepository;
import com.shavizu.SHAVIZUSpringboot.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {

    private final ShopRepository shopRepository;

    @Override
    public AuthDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        shopRepository.findByUserId(username)
                .orElseThrow(() -> NotFoundException.USER_NAME_NOT_FOUND);

        return new AuthDetails(username);
    }

}
