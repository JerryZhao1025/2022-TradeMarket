package com.laioffer.tradeMarket.controller;

import com.laioffer.tradeMarket.entity.Tag;
import com.laioffer.tradeMarket.service.PostService;
import com.laioffer.tradeMarket.service.TagService;
import com.laioffer.tradeMarket.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class TagController {
    private final PostService postService;
    private final TagService tagService;

    private final TokenService tokenService;

    @Autowired
    public TagController(PostService postService, TagService tagService, TokenService tokenService) {
        this.postService = postService;
        this.tagService = tagService;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = {"/post/{postID}/addTag/{tagID}"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addTag(@PathVariable("postID") int postId, @PathVariable("tagID") int tagId,
                       @RequestHeader("Authorization") String token, HttpServletResponse response) {
        if (!tokenService.verify(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        String username = tokenService.getUsernameFromToken(token);
        if (!postService.verifyPost(postId, username)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        tagService.appendTag(tagId, postId);
    }

    @RequestMapping(value = {"/post/{postID}/removeTag/{tagID}"}, method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeTag(@PathVariable("postID") int postId, @PathVariable("tagID") int tagId,
                          @RequestHeader("Authorization") String token, HttpServletResponse response) {
        if (!tokenService.verify(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        String username = tokenService.getUsernameFromToken(token);
        if (!postService.verifyPost(postId, username)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        tagService.removeTag(tagId, postId);
    }

    @RequestMapping(value = {"/tags"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }
}
