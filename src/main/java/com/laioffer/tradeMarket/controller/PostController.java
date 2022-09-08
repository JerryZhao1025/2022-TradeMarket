package com.laioffer.tradeMarket.controller;

import com.laioffer.tradeMarket.entity.Media;
import com.laioffer.tradeMarket.entity.Post;
import com.laioffer.tradeMarket.entity.Tag;
import com.laioffer.tradeMarket.service.PostService;
import com.laioffer.tradeMarket.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

public class PostController {
    private final PostService postService;

    private TagService tagService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = {"/post/newPost"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addPost(@PathVariable("postID") int postID,
                         HttpServletResponse response) {
        // logic Tire
//        postService.method(att1, attr2);
    }

    @RequestMapping(value = {"/post/{postID}/edit"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void editPost(@PathVariable("postID") int postID, @RequestBody Post post,
                         HttpServletResponse response) {
        // logic Tire
//        postService.method(att1, attr2);
    }

    @RequestMapping(value = {"/post/{postID}/delete"}, method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePost(@PathVariable("postID") int postID, @RequestBody Post post,
                         HttpServletResponse response) {
        // logic Tire
//        postService.method(att1, attr2);
    }


    @RequestMapping(value = {"/post/{postID}/addTag"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addTag(@PathVariable("postID") int tagId, int postId, @RequestBody Tag tag,
                           HttpServletResponse response) {
        tagService.addTag(tagId, postId);
    }

    @RequestMapping(value = {"/post/{postID}/removeTag"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeTag(@PathVariable("postID") int tagId, int postId, @RequestBody Tag tag,
                       HttpServletResponse response) {
        tagService.removeTag(tagId, postId);
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
