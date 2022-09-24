package com.laioffer.tradeMarket.service;

import com.laioffer.tradeMarket.dao.PostDao;
import com.laioffer.tradeMarket.dao.TagDao;
import com.laioffer.tradeMarket.dao.UserDao;
import com.laioffer.tradeMarket.entity.Post;
import com.laioffer.tradeMarket.entity.Tag;
import com.laioffer.tradeMarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
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

    public Post addPost(Post post, User user) {
        post.setPostTime(Timestamp.from(Instant.now()));
        post.setMedias(new ArrayList<>());
        post.setAppendTags(new ArrayList<>());
        post.setOwner(user);
        return postDao.addPost(post);
    }

    public Post editPost(int postID, Post newPost) {
        return postDao.editPost(postID, newPost);
    }

    public Post deletePost(int postID) {
        // 我认为这里还是需要用一下Authentication的方法进行验证，如果没有验证的话，就有可能所有人都可以通过一个组件随意删除网站上的post
        return postDao.deletePost(postID);
    }


    public List<Post> getAllPostsByTag(int tagId) {
        return tagDao.getAllPostsByTagId(tagId);
    }

    public List<Post> getAllPostsByKeyword(String word) {
        return postDao.getAllPostsByKeyword(word);
    }

    public List<Post> getPostsByTagAndKeyword(int tagId, String keyword) {
        return postDao.getPostsByTagAndKeyword(tagId, keyword);
    }
}