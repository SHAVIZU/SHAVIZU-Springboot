package com.shavizu.SHAVIZUSpringboot.security.auth;

import com.shavizu.SHAVIZUSpringboot.exception.UnAuthorizedException;
import com.shavizu.SHAVIZUSpringboot.property.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;

    public Authentication getAuthentication(String token) {
        Jws<Claims> jws = getJws(token);
        if (!isAccessToken(jws)) {
            throw UnAuthorizedException.INVALID_TOKEN;
        }

        AuthDetails authDetails = authDetailsService.loadUserByUsername(getUsername(jws));
        return new UsernamePasswordAuthenticationToken(authDetails, "", authDetails.getAuthorities());
    }

    private Jws<Claims> getJws(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
        } catch(ExpiredJwtException e) {
            throw UnAuthorizedException.EXPIRED_TOKEN;
        } catch(RuntimeException e) {
            throw UnAuthorizedException.INVALID_TOKEN;
        }
    }

    private boolean isAccessToken(Jws<Claims> jws) {
        return jws.getHeader().get("typ").toString().equals(JwtProperties.ACCESS_TYPE);
    }

    private String getUsername(Jws<Claims> jws) {
        return jws.getBody().getSubject();
    }

}
