package com.shavizu.SHAVIZUSpringboot.controller;

import com.shavizu.SHAVIZUSpringboot.dto.request.LoginRequest;
import com.shavizu.SHAVIZUSpringboot.security.jwt.TokenResponse;
import com.shavizu.SHAVIZUSpringboot.service.auth.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final LoginService loginService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.execute(request);
    }

}
