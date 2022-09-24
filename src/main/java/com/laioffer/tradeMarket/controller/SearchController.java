package com.laioffer.tradeMarket.controller;

import com.laioffer.tradeMarket.entity.Post;
import com.laioffer.tradeMarket.service.PostService;
import com.laioffer.tradeMarket.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class SearchController {
    private final PostService postService;
    private final TagService tagService;

    @Autowired
    public SearchController(PostService postService, TagService tagService) {
        this.postService = postService;
        this.tagService = tagService;
    }

    @RequestMapping(value = {"/searchPosts"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Set<Post> getPostsByTag(@RequestParam(value = "tag", required = false) Optional<Integer> optionalTagId,
                                  @RequestParam(value = "keyword", required = false) Optional<String> optionalKeyWord) {
        if (optionalTagId.isPresent() && optionalKeyWord.isPresent()) {
            int tagId = optionalTagId.get();
            String keyword = optionalKeyWord.get();
            return postService.getPostsByTagAndKeyword(tagId, keyword);
        } else if (optionalTagId.isPresent()) {
            int tagId = optionalTagId.get();
            return postService.getAllPostsByTag(tagId);
        } else if (optionalKeyWord.isPresent()) {
            String keyword = optionalKeyWord.get();
            return postService.getAllPostsByKeyword(keyword);
        } else {
            return postService.getAllPostsByKeyword("");
        }
    }
}
    
