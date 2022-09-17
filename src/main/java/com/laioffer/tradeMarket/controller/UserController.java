package com.laioffer.tradeMarket.controller;

import com.laioffer.tradeMarket.entity.User;
import com.laioffer.tradeMarket.service.PostService;
import com.laioffer.tradeMarket.service.TagService;
import com.laioffer.tradeMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    private final PostService postService;
    private final TagService tagService;
    private final UserService userService;

    @Autowired
    public UserController(PostService postService, TagService tagService, UserService userService) {
        this.postService = postService;
        this.tagService = tagService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/user/{username}"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public User searchUserByUserID(@PathVariable("username") String username, HttpServletResponse response){
        return userService.getUserByUsername(username);
    }
}
