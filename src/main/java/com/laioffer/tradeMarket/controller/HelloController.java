package com.laioffer.tradeMarket.controller;

import com.laioffer.tradeMarket.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {
    @Autowired
    private TokenService tokenService;
    @RequestMapping("hello")
    @ResponseBody
    public String helloWorld(@RequestParam("username") String username, @RequestParam("token") String token,
                             HttpServletResponse response) {

        tokenService.verify(token, username);
//            return "Hello "+username+"!!";
//        }

        return "not right token";
    }
}
