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
    public void hello(HttpServletResponse response, @RequestHeader("Authorization") String token) throws IOException {
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
