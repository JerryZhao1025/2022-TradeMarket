package com.laioffer.tradeMarket.dao;


import com.laioffer.tradeMarket.entity.Post;
import com.laioffer.tradeMarket.entity.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class TagDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Tag getTag(int tagId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.get(Tag.class, tagId);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    //addTag and deleteTag 应该放在tagDao还是postDao？
    public void addTag(int tagId, int postId){
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Post post = session.get(Post.class, postId);
            Tag tag = session.get(Tag.class, tagId);
            post.getAppendTags().add(tag);

            session.beginTransaction();
            session.save(tag);
            session.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
    }

    public void removeTag(int tagId, int postId){
        Session session = null;

        try {
            session = sessionFactory.openSession();

            Post post = session.get(Post.class, postId);
            Tag tag = session.get(Tag.class, tagId);
            post.getAppendTags().remove(tag);

            session.beginTransaction();
            session.remove(tag);
            session.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
    }




}
