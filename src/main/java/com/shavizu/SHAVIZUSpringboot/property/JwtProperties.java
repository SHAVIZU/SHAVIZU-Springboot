package com.shavizu.SHAVIZUSpringboot.property;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties("jwt")
public class JwtProperties {

    public static final String ACCESS_TYPE = "access";

    private static final Integer MICROSECOND_PER_SECOND = 1000;

    private final String secretKey;
    private final Long accessExp;

    public JwtProperties(String secretKey, Long accessExp) {
        this.secretKey = secretKey;
        this.accessExp = accessExp * MICROSECOND_PER_SECOND;
    }

}
