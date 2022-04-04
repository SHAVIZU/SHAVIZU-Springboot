package com.shavizu.SHAVIZUSpringboot.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    @NotBlank
    @Length(min = 6, max = 12)
    private String userId;

    @NotBlank
    @Length(min = 8, max = 20)
    private String password;

}
