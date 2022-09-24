package com.laioffer.tradeMarket.controller;

import com.laioffer.tradeMarket.entity.Tag;
import com.laioffer.tradeMarket.service.PostService;
import com.laioffer.tradeMarket.service.TagService;
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

    @Autowired
    public TagController(PostService postService, TagService tagService) {
        this.postService = postService;
        this.tagService = tagService;
    }

    @RequestMapping(value = {"/post/{postID}/addTag/{tagID}"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addTag(@PathVariable("postID") int postId, @PathVariable("tagID") int tagId,
                       HttpServletResponse response) {
        tagService.appendTag(tagId, postId);
    }

    @RequestMapping(value = {"/post/{postID}/removeTag/{tagID}"}, method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeTag(@PathVariable("postID") int postId, @PathVariable("tagID") int tagId,
                          HttpServletResponse response) {
        tagService.removeTag(tagId, postId);
    }

    @RequestMapping(value = {"/tags"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }
}
