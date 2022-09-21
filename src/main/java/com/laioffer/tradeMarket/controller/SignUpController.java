package com.laioffer.tradeMarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.tradeMarket.entity.User;
import com.laioffer.tradeMarket.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SignUpController {

    private final UserService userService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void signUp(@RequestBody User user, HttpServletResponse response) throws IOException {
        try {
            userService.signUp(user);
            response.setStatus(HttpStatus.CREATED.value());

            Map<String, Object> claims = new HashMap<>();
            claims.put("username", user.getUsername());
            claims.put("password", user.getPassword());

            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            String jws = Jwts.builder().setClaims(claims).signWith(key).compact(); // token

            Map<String, Object> claim2 = Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody();
            System.out.println();
            System.out.println(claim2.get("password"));

            response.getOutputStream().println(jws);
            response.getOutputStream().println((String)claim2.get("username"));
            response.getOutputStream().println((String)claim2.get("password"));

        } catch (Exception exception) {
            response.setStatus(HttpStatus.CONFLICT.value());
            Map<String, Object> data = new HashMap<>();
            data.put("message", exception.getMessage());
            response.getOutputStream().println(objectMapper.writeValueAsString(data));
        }
    }
}
