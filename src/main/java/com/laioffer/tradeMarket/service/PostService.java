package com.laioffer.tradeMarket.service;

import com.laioffer.tradeMarket.dao.PostDao;
import com.laioffer.tradeMarket.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostDao postDao;

    @Autowired
    public PostService(PostDao postDao) {
        this.postDao = postDao;
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
}