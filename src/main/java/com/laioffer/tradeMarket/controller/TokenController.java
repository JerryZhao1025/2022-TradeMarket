package com.laioffer.tradeMarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.tradeMarket.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/menus")
    public void hello(HttpServletResponse response, @RequestHeader("Authorization") String token) throws IOException {
        //String token = "eyJhbGciOiJIUzI1NiJ9.eyJ0b2tlbktleSI6IlRoaXMgaXMgb3VyIGtleSBvZiBGTEFHQ0FNUCIsInVzZXJuYW1lIjoiZmZmd3d3bGxsbCJ9.nKbcypsSwJGBUZbOtB2smKR1bNO6GY5nZghmbg2tFkg";
        token = token.substring(7);
        boolean verifySuccess = tokenService.verify(token);
        if (!verifySuccess) {

            response.setStatus(HttpStatus.CONFLICT.value());
            Map<String, Object> data = new HashMap<>();
            data.put("message", "login Fail");
            response.getOutputStream().println(objectMapper.writeValueAsString(data));
        }
        System.out.println("Login Success");
    }
}
