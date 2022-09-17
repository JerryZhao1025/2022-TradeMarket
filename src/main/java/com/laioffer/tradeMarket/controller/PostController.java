package com.laioffer.tradeMarket.controller;

import com.laioffer.tradeMarket.entity.Media;
import com.laioffer.tradeMarket.entity.Post;
import com.laioffer.tradeMarket.entity.Tag;
import com.laioffer.tradeMarket.entity.User;
import com.laioffer.tradeMarket.service.PostService;
import com.laioffer.tradeMarket.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
public class PostController {
    @Autowired
    private final PostService postService;
    private final TagService tagService;

    @Autowired
    public PostController(PostService postService, TagService tagService) {
        this.postService = postService;
        this.tagService = tagService;
    }

    @RequestMapping(value = {"/post/newPost"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addPost(@RequestBody Post post,
                         HttpServletResponse response) {
        // TODO: fill in user information into the post
        postService.addPost(post);
    }

    @RequestMapping(value = {"/post/{postID}/edit"}, method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void editPost(@PathVariable("postID") int postID, @RequestBody Post post,
                         HttpServletResponse response) {
        postService.editPost(postID, post);
    }

    @RequestMapping(value = {"/post/{postID}/delete"}, method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePost(@PathVariable("postID") int postID, HttpServletResponse response) {
        postService.deletePost(postID);
    }


    @RequestMapping(value = {"/post/{postID}/addTag"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addTag(@PathVariable("postID") int tagId, int postId, @RequestBody Tag tag,
                           HttpServletResponse response) {
        tagService.addTag(tagId, postId);
    }

    @RequestMapping(value = {"/post/{postID}/addTag/{tagID}"}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addTag(@PathVariable("postID") int postId, @PathVariable("tagID") int tagId,
                       HttpServletResponse response) {
        tagService.addTag(tagId, postId);
    }

    @RequestMapping(value = {"/post/{postID}/removeTag/{tagID}"}, method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeTag(@PathVariable("postID") int postId, @PathVariable("tagID") int tagId,
                          HttpServletResponse response) {
        tagService.removeTag(tagId, postId);
    }

    @RequestMapping(value = {"/post/{postID}"}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Post editPost(@PathVariable("postID") int postID, HttpServletResponse response) {
        return postService.getPost(postID);
    }

    // =========================坚决不要动这下面的code，让做media的同学自己搞，不然可能会有冲突=============================

    @PostMapping("/upload")
    @ResponseStatus(value = HttpStatus.OK)
    public void uploadFile(@RequestParam(value = "file") MultipartFile file){
        postService.uploadFile(file);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName){
        byte[] data = postService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename = \"" + fileName + "\"")
                .body(resource);
    }
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName){
        return new ResponseEntity<>(postService.deleteFile(fileName), HttpStatus.OK);
    }

//    @RequestMapping(value = {"/post/{postID}/addMedia"}, method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.OK)
//    public void addMedia(@PathVariable("postID") int postID, @RequestBody Media media,
//                         HttpServletResponse response) {
//        //Note: 这里的input @RequestBody Media media 也许可以是一个list of media，尝试实现。
//        // logic Tire
////        postService.method(att1, attr2);
//    }

}