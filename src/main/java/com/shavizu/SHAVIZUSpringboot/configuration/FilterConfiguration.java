package com.shavizu.SHAVIZUSpringboot.configuration;

import com.shavizu.SHAVIZUSpringboot.exception.handler.ShavizuExceptionHandlingFilter;
import com.shavizu.SHAVIZUSpringboot.security.auth.AuthenticationProvider;
import com.shavizu.SHAVIZUSpringboot.security.jwt.JwtFilter;
import com.shavizu.SHAVIZUSpringboot.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtProvider jwtProvider;
    private final AuthenticationProvider authenticationProvider;

    @Override
    public void configure(HttpSecurity builder) {
        JwtFilter jwtFilter = new JwtFilter(jwtProvider, authenticationProvider);
        ShavizuExceptionHandlingFilter exceptionHandlingFilter = new ShavizuExceptionHandlingFilter();

        builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        builder.addFilterBefore(exceptionHandlingFilter, JwtFilter.class);
    }

}
