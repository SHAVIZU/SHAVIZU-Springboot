package com.shavizu.SHAVIZUSpringboot.property;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties("jwt")
public class JwtProperties {

    public static final String ACCESS_TYPE = "access";
    public static final String REFRESH_TYPE  = "refresh";

    private static final Integer MICROSECOND_PER_SECOND = 1000;

    private final String secretKey;
    private final Long accessExp;
    private final Long refreshExp;

    public JwtProperties(String secretKey, Long accessExp, Long refreshExp) {
        this.secretKey = secretKey;
        this.accessExp = accessExp * MICROSECOND_PER_SECOND;
        this.refreshExp = refreshExp * MICROSECOND_PER_SECOND;
    }

}
