package com.laioffer.tradeMarket.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;

import java.util.Properties;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    String tokenKey = "This is our key of FLAGCAMP";

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String getJWTToken(String username) throws IOException {

//        Properties properties = new Properties();
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
//        properties.load(inputStream);
//
//        String tokenKey = properties.getProperty("tokenKey");

        String tokenKey = "This is our key of FLAGCAMP";

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("tokenKey", tokenKey);
        //Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String token = Jwts.builder().setClaims(claims).signWith(key).compact();
        System.out.println("Generate token: ->" + token);
//        Map<String, Object> claims2 = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
//        String tokenKeyReceived = (String)claims2.get("tokenKey");

        return token;

    }

    public boolean verify(String token) {
        try {
            System.out.println("Token from frontend: ->" + token);
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
