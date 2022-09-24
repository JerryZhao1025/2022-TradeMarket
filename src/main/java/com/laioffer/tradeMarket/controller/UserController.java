package com.laioffer.tradeMarket.controller;

import com.laioffer.tradeMarket.entity.User;
import com.laioffer.tradeMarket.service.PostService;
import com.laioffer.tradeMarket.service.TagService;
import com.laioffer.tradeMarket.service.TokenService;
import com.laioffer.tradeMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    private final UserService userService;

    private final TokenService tokenService;

    @Autowired
    public UserController(PostService postService, TagService tagService, UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = {"/user/{username}"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public User searchUserByUsername(@PathVariable("username") String username, @RequestHeader("Authorization") String token,
                                     HttpServletResponse response){
        if (!tokenService.verify(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }

        if (tokenService.getUsernameFromToken(token).equals(username)){ //get all user info if token matches current user
            return userService.getUser(username);
        } else { // get part user info if it's a visitor
            return userService.getPartUserInfo(username);
        }
    }
}
