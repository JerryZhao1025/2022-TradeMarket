package com.laioffer.tradeMarket.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Key;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    String tokenKey = "This is our key of FLAGCAMP";

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String getJWTToken(String username) throws IOException {
        String tokenKey = "This is our key of FLAGCAMP";
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("tokenKey", tokenKey);
        String token = Jwts.builder().setClaims(claims).signWith(key).compact();
        System.out.println("Generate token: ->" + token);
        return token;
    }

    public boolean verify(String token) {
        token = token.substring(7);
        System.out.println("Token from frontend: ->" + token);
        try {
            Map<String, Object> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            String username = (String)claims.get("username");
            String tokenKeyReceived = (String)claims.get("tokenKey");
            if (!tokenKeyReceived.equals(tokenKey)) {
                // wrong token key
                return false;
            }
            return true;
        } catch (JwtException e) {
            return false;
            //don't trust the JWT!
        }
    }
}
