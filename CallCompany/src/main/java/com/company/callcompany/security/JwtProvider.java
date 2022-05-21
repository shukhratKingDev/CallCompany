package com.company.callcompany.security;

import com.company.callcompany.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {
    private final long expireTime=1000000000;
    private final String key="Secret";
    public String generateJwt(String username, Set<Role> roles){
        String jwt = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .claim("roles",roles)
                .signWith(SignatureAlgorithm.HS512, key)

                .compact();
        return jwt;
    }
    public String getUsernameFromToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        }catch (Exception e){
            return null;
        }
    }
}
