package com.laioffer.tradeMarket.dao;

import com.laioffer.tradeMarket.entity.Post;
import com.laioffer.tradeMarket.entity.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TagDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public TagDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
            session.update(post);
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
            session.update(post);
            session.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
    }

    public Set<Post> getAllPostsByTagId(int tagId){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Tag tag = session.get(Tag.class, tagId);
            if (tag != null){
                return tag.getPosts();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null ){
                session.close();
            }
        }
        return new HashSet<>();
    }

    public List<Tag> getAllTags() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
            Root<Tag> tags = criteria.from(Tag.class);
            CriteriaQuery<Tag> all = criteria.select(tags);
            return session.createQuery(all).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
}
