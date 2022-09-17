package com.laioffer.tradeMarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.tradeMarket.entity.User;
import com.laioffer.tradeMarket.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SignUpController {
    @Autowired
    private UserService userService;



    private final ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void signUp(@RequestBody User user, HttpServletResponse response) throws IOException {
        try {
            userService.signUp(user);
//            response.setStatus(HttpStatus.CREATED.value());
//
//            Map<String, Object> claims = new HashMap<>();
//            claims.put("username", user.getUsername());
//            claims.put("password", user.getPassword());
//
//            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//            String jws = Jwts.builder().setClaims(claims).signWith(key).compact();
//            response.getOutputStream().println(jws);
        } catch (Exception exception) {
            response.setStatus(HttpStatus.CONFLICT.value());
            Map<String, Object> data = new HashMap<>();
            data.put("message", exception.getMessage());
            response.getOutputStream().println(objectMapper.writeValueAsString(data));
        }

    }
}
