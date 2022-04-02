package com.shavizu.SHAVIZUSpringboot.security.jwt;

import com.shavizu.SHAVIZUSpringboot.property.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private static final String PREFIX = "Bearer";

    private final JwtProperties jwtProperties;

    //토큰 생성
    public TokenResponse generateToken(String username) {
        return new TokenResponse(
          generateToken(username, jwtProperties.getAccessExp(), JwtProperties.ACCESS_TYPE),
          generateToken(username, jwtProperties.getRefreshExp(), JwtProperties.REFRESH_TYPE)
        );
    }

    private String generateToken(String username, Long expiration, String type) {
        return Jwts.builder()
                .setSubject(username)
                .setHeaderParam("typ", type)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    //Bearer 토큰 추출
    public String resolveToken(String token) {
        if (token != null
                && token.startsWith(PREFIX)
                && token.length() > PREFIX.length()) {
            return token.substring(PREFIX.length() + 1);
        }
        return null;
    }

}
