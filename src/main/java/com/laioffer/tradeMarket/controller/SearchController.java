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
import java.util.Optional;
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
    // @RequestMapping(value = {"/searchByTagId/{tagID}"}, method = RequestMethod.GET)
    // @ResponseStatus(value = HttpStatus.OK)
    // public Set<Post> getPostsByTag(@PathVariable("tagID") int tagId, @RequestBody Tag tag, HttpServletResponse response){
    //     return tagService.getAllPosts(tagId);
    // }

    // @RequestMapping(value = {"/searchByWord/{keyWord}"}, method = RequestMethod.GET)
    // @ResponseStatus(value = HttpStatus.OK)
    // public Set<Post> getPostsByTag(@PathVariable("keyWord") String keyWord, HttpServletResponse response){
    //     return tagService.getAllPosts(keyWord);
    // }

   @RequestMapping(value = {"/searchPosts"}, method = RequestMethod.GET)
   @ResponseStatus(value = HttpStatus.OK)
   public List<Post> getPostsByTag(@RequestParam(value = "tag", required = false) Optional<Integer> optionalTagId,
                                  @RequestParam(value = "keyword", required = false) Optional<String> optionalKeyWord, 
                                  @RequestParam(value = "quantity", required = false) Optional<Integer> optionalPostQuantity, 
                                  HttpServletResponse response) {

        int DEFAULT_POST_NUM = 10;                       
        int returnedPostNum = optionalPostQuantity.orElse(DEFAULT_POST_NUM);

        if (optionalTagId.isPresent()) {
            int tagId = optionalTagId.get();
            return tagService.getAllPosts(tagId).stream().toList(); // TODO: Need to controller the total return posts number.
        } else if (optionalKeyWord.isPresent()) {
            String keyword = optionalKeyWord.get(); // TODO: Need to controller the total return posts number.
            return postService.getAllPostsByKeyword(keyword);
        } else { // search by default? return top 10 
            // TODO: Need to controller the total return posts number.
            return null;
        }
   }

    @RequestMapping(value = {"/searchByTagID/{tagID}"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Set<Post> getPostsByTag1(@PathVariable("tagID") int tagId, HttpServletResponse response){
        return tagService.getAllPosts(tagId);
    }
}
    
