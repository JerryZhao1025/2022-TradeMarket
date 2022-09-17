package com.laioffer.tradeMarket.controller;

import com.laioffer.tradeMarket.entity.User;
import com.laioffer.tradeMarket.service.PostService;
import com.laioffer.tradeMarket.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    private final PostService postService;
    private final TagService tagService;

    @Autowired
    public UserController(PostService postService, TagService tagService) {
        this.postService = postService;
        this.tagService = tagService;
    }

    @RequestMapping(value = {"/user/{userID}"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public User searchUserByUserID(@PathVariable("userID") int userID, HttpServletResponse response){
        return postService.searchUserByID(userID);
    }
}
