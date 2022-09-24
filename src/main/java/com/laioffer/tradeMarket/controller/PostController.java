package com.laioffer.tradeMarket.controller;

import com.laioffer.tradeMarket.entity.Media;
import com.laioffer.tradeMarket.entity.Post;

import com.laioffer.tradeMarket.entity.User;
import com.laioffer.tradeMarket.service.PostService;
import com.laioffer.tradeMarket.service.TagService;
import com.laioffer.tradeMarket.service.TokenService;
import com.laioffer.tradeMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class PostController {
    private final PostService postService;
    private final TagService tagService;
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, TagService tagService, TokenService tokenService, UserService userService) {
        this.postService = postService;
        this.tagService = tagService;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/post/newPost"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public Post addPost(@RequestBody Post post, @RequestHeader("Authorization") String token,
                         HttpServletResponse response) {
        if (!tokenService.verify(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
        String username = tokenService.getUsernameFromToken(token);
        User user = userService.getUser(username);
        return postService.addPost(post, user);
    }

    @RequestMapping(value = {"/post/{postID}/edit"}, method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public Post editPost(@PathVariable("postID") int postID, @RequestHeader("Authorization") String token, @RequestBody Post newpost,
                         HttpServletResponse response) {
        if (!tokenService.verify(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
        String username = tokenService.getUsernameFromToken(token);
        if (!postService.verifyPost(postID, username)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
        return postService.editPost(postID, newpost);
        // 目前仅支持对post的Title, Description和Price做改动
    }

    @RequestMapping(value = {"/post/{postID}/delete"}, method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Post deletePost(@PathVariable("postID") int postID, @RequestHeader("Authorization") String token, HttpServletResponse response) {
        if (!tokenService.verify(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
        String username = tokenService.getUsernameFromToken(token);
        if (!postService.verifyPost(postID, username)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
        return postService.deletePost(postID);
    }

    @RequestMapping(value = {"/post/{postID}"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Post getPost(@PathVariable("postID") int postID, HttpServletResponse response) {
        return postService.getPost(postID);
    }
    // =========================坚决不要动这下面的code，让做media的同学自己搞，不然可能会有冲突=============================


    @RequestMapping(value = {"/post/{postID}/addMedia"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addMedia(@PathVariable("postID") int postID, @RequestBody Media media,
                         HttpServletResponse response) {
        //Note: 这里的input @RequestBody Media media 也许可以是一个list of media，尝试实现。
        // logic Tire
//        postService.method(att1, attr2);
    }

}