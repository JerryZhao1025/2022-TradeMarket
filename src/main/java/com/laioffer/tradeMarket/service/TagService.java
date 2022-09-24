package com.laioffer.tradeMarket.service;

import com.laioffer.tradeMarket.dao.TagDao;
import com.laioffer.tradeMarket.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TagService {
    private final TagDao tagDao;

    @Autowired
    public TagService(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public void appendTag(int tagId, int postId) {
        tagDao.addTag(tagId, postId);
    }

    public void removeTag(int tagId, int postId) {
        tagDao.removeTag(tagId, postId);
    }

<<<<<<< HEAD
    public Set<Post> getAllPosts(int tagId){
        return tagDao.getAllPostsByTagId(tagId);
    }

=======
>>>>>>> 02a8e9a (supports searching posts with a up limit)
}
