package com.laioffer.tradeMarket.service;

import com.laioffer.tradeMarket.dao.PostDao;
import com.laioffer.tradeMarket.dao.TagDao;
import com.laioffer.tradeMarket.dao.UserDao;
import com.laioffer.tradeMarket.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PostService {
    private final PostDao postDao;
    private final UserDao userDao;
    private final TagDao tagDao;

    @Autowired
    public PostService(PostDao postDao, UserDao userDao, TagDao tagDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.tagDao = tagDao;
    }

    public Post getPost(int postID) {
        return postDao.getPostById(postID);
    }

    public void addPost(Post post) {
        postDao.addPost(post);
    }

    public void editPost(int postID, Post newPost) {
        postDao.editPost(postID, newPost);
    }

    public void deletePost(int postID) {
        // 我认为这里还是需要用一下Authentication的方法进行验证，如果没有验证的话，就有可能所有人都可以通过一个组件随意删除网站上的post
        postDao.deletePost(postID);
    }


    public List<Post> getAllPostsByTag(int tagId) {
        return tagDao.getAllPostsByTagId(tagId);
    }

    public List<Post> getAllPostsByKeyword(String word) {
        return postDao.getAllPostsByKeyword(word);
    }

    public List<Post> getPostsByTagAndKeyword(int tagID, String keyword) {
        return postDao.getPostsByTagAndKeyword(tagID, keyword);
    }
}