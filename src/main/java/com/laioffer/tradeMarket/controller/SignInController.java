package com.laioffer.tradeMarket.controller;

import com.laioffer.tradeMarket.entity.Authorities;
import com.laioffer.tradeMarket.entity.User;

import com.laioffer.tradeMarket.service.TokenService;
import com.laioffer.tradeMarket.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public class SignInController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/signin", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String pwd,
                                    HttpServletResponse response) {
        User user = userService.getUser(username);
        JSONObject result = new JSONObject();
        // login success
        if (user != null && user.getPassword().equals(pwd)) {
            String token = tokenService.getJWTToken(username, pwd);
            result.put("token", token);
            return result.toString();
        }
        // login failed
        return null;
    }
}
