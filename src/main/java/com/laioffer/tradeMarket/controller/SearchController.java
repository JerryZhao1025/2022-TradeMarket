package com.laioffer.tradeMarket.controller;

import com.laioffer.tradeMarket.entity.Post;
import com.laioffer.tradeMarket.entity.Tag;
import com.laioffer.tradeMarket.service.PostService;
import com.laioffer.tradeMarket.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@Controller
public class SearchController {
    private final PostService postService;
    private final TagService tagService;

    @Autowired
    public SearchController(PostService postService, TagService tagService) {
        this.postService = postService;
        this.tagService = tagService;
    }

    @RequestMapping(value = {"/searchByTagId/{tagID}"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Set<Post> getPostsByTag(@PathVariable("tagID") int tagId, @RequestBody Tag tag, HttpServletResponse response){
        return tagService.getAllPosts(tagId);

    }

    @RequestMapping(value = {"/searchByWord/{keyWord}"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Set<Post> getPostsByTag(@PathVariable("keyWord") String keyWord, HttpServletResponse response){
        return tagService.getAllPosts(keyWord);

    }


//    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public Set<Post> getPostsByTag(@RequestParam(value = "tag") int tagId, @RequestBody Tag tag, HttpServletResponse response){
//        return tagService.getAllPosts(tagId);
//
//    }
//
//    "/search?word=lalala"
//
//    @RequestMapping(value = {"/search/word/{keyWord}"}, method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public Set<Post> getPostsByTag(@PathVariable("keyWord") String keyWord, HttpServletResponse response){
//        return tagService.getAllPosts(keyWord);
//
//    }
}
