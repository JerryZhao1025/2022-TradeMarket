package com.laioffer.tradeMarket.controller;

import com.laioffer.tradeMarket.entity.Media;
import com.laioffer.tradeMarket.entity.Post;
import com.laioffer.tradeMarket.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = {"/post/newPost"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addPost(@RequestBody Post post,
                         HttpServletResponse response) {
        postService.addPost(post);
    }

    @RequestMapping(value = {"/post/{postID}/edit"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void editPost(@PathVariable("postID") int postID, @RequestBody Post post,
                         HttpServletResponse response) {
        postService.editPost(postID, post);
    }

    @RequestMapping(value = {"/post/{postID}/delete"}, method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePost(@PathVariable("postID") int postID, @RequestBody Post post,
                         HttpServletResponse response) {
        postService.deletePost(postID);
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
