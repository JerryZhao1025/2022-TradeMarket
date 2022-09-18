package com.laioffer.tradeMarket.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.security.Key;

import java.util.Date;

@Service
public class TokenService {
    String FLAGKey = "This is our key of FLAGCAMP";
    public String getJWTToken(String username, String password) {
//        String token = Jwts.builder().setSubject(username)//.signWith(key).compact();
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 600000))
//                .signWith(SignatureAlgorithm.HS512,
//                        FLAGKey)
//                .compact();
//        String token = Jwts
//                .builder()
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 600000))
//                .signWith(SignatureAlgorithm.HS512,
//                        FLAGKey).compact();
        String token = Jwts.builder().setSubject("Joe").signWith(SignatureAlgorithm.HS512,  FLAGKey).compact();

        return token;

    }

    public void verify(String token, String username) {
//        try {
//            Jwts.parserBuilder().setSigningKey(FLAGKey).build().parseClaimsJws(token);
//        } catch (JwtException e) {
//
//            //don't trust the JWT!
//        }
    }
}
