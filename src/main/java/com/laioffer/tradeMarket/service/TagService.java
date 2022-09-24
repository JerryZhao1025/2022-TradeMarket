package com.laioffer.tradeMarket.service;

import com.laioffer.tradeMarket.dao.TagDao;
import com.laioffer.tradeMarket.entity.Post;
import com.laioffer.tradeMarket.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Post> getAllPosts(int tagId){
        return tagDao.getAllPostsByTagId(tagId);
    }

    public List<Tag> getAllTags() {
        List<Tag> tags = tagDao.getAllTags();
        for (Tag tag : tags) {
            tag.setPosts(null);
        }
        return tags;
    }
}
