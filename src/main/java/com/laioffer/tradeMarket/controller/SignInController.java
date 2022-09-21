package com.laioffer.tradeMarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.tradeMarket.entity.User;

import com.laioffer.tradeMarket.service.TokenService;
import com.laioffer.tradeMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SignInController {

    private final UserService userService;
    private final TokenService tokenService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public SignInController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public void signIn(@RequestBody User user, HttpServletResponse response) throws IOException {
        try {
            User userInDB = userService.getUser(user.getUsername());
            if (user.getPassword().equals(userInDB.getPassword())) {

                response.setStatus(HttpStatus.CREATED.value());
                String token = tokenService.getJWTToken(user.getUsername());
                response.getOutputStream().println(token);
            } else {
                // wrong password
                response.setStatus(HttpStatus.CONFLICT.value());
                Map<String, Object> data = new HashMap<>();
                data.put("message", "Wrong Password");
                response.getOutputStream().println(objectMapper.writeValueAsString(data));
            }

        } catch (Exception exception) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            Map<String, Object> data = new HashMap<>();
            data.put("message", exception.getMessage());
            response.getOutputStream().println(objectMapper.writeValueAsString(data));
        }

    }
}
