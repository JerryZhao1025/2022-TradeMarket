package com.laioffer.tradeMarket.dao;

import com.laioffer.tradeMarket.entity.Post;
import com.laioffer.tradeMarket.entity.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class PostDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PostDao(SessionFactory sessionFactory) throws Exception {
        this.sessionFactory = sessionFactory;
    }

    public Post getPostById(int postID) {
        try (Session session = sessionFactory.openSession()) {
            // Post.class这张表里get到postId对应的那一行(用Post类型返回)
            // 我们只从一张表里找一行东西的时候我们就可以直接用session.get
            Post post = session.get(Post.class, postID);
            if ( post != null) {
                return post;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Post();
    }

    public Post addPost(Post newPost) {
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(newPost);
            session.getTransaction().commit();
            return newPost;
        } catch (Exception ex){
            ex.printStackTrace();
            if(session != null) session.getTransaction().rollback();
        } finally {
            if (session != null){
                session.close();
            }
        }
        return null;
    }

    public Post editPost(int postID, Post newPost) {
        Post post = getPostById(postID);
        // 改变post底下的所有参数(仅用户可以改变的部分)
        post.setTitle(newPost.getTitle());
        post.setDescription(newPost.getDescription());
        post.setPrice(newPost.getPrice());

        Session session = null;
        try {
            session = sessionFactory.openSession();
            //强烈建议所有对数据库的操作都定义在一个transaction底下，因为这个可以保证原子性
            //Especially我们在对关系型数据库做改动的时候！！
            session.beginTransaction();
            // 我们这里是改变了post，需要存到数据库里
            session.update(post);
            session.getTransaction().commit();
        } catch (Exception e) { // for all other db saving exceptions
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return post;
    }

    public Post deletePost(int postID) {
        Post post = getPostById(postID);
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(post);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            if(session != null){
                session.getTransaction().rollback();
            }

        } finally {
            if(session != null){
                session.close();
            }
        }
        return post;
    }

    //needs to update
    public Set<Post> getAllPostsByKeyword(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Post> criteria = builder.createQuery(Post.class);
            Root<Post> posts = criteria.from(Post.class);
            Predicate titleMatch = builder.like(posts.get("title"), "%" + keyword + "%");
            Predicate descriptionMatch = builder.like(posts.get("description"), "%" + keyword + "%");
            criteria.where(builder.or(titleMatch, descriptionMatch));
            // 然后把这个query给session运行并返回结果
            return new HashSet<>(session.createQuery(criteria).getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new HashSet<>();
    }

    public Set<Post> getPostsByTagAndKeyword(int tagId, String keyword) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Post> criteria = builder.createQuery(Post.class);
            Root<Post> posts = criteria.from(Post.class);
//            List<Predicate> predicates = new ArrayList<>();
//            for (int tagId : tags){
//                Subquery<Integer> subquery =  criteria.subquery(Integer.class);
//                Root<Post> subPost = subquery.from(Post.class);
//                Join<Tag, Post> subTag = subPost.join("appendTags");
//
//                subquery
//                        .select(subPost.get("id"))
//                        .where(builder.equal(subTag.get("id"), tagId));
//
//                predicates.add(builder.in(posts.get("id")).value(subquery));
//            }

            Subquery<Integer> subquery =  criteria.subquery(Integer.class);
            Root<Post> subPost = subquery.from(Post.class);
            Join<Tag, Post> subTag = subPost.join("appendTags");

            subquery
                    .select(subPost.get("id"))
                    .where(builder.equal(subTag.get("id"), tagId));
            Predicate tagMatch = builder.in(posts.get("id")).value(subquery);

            Predicate titleMatch = builder.like(posts.get("title"), "%" + keyword + "%");
            Predicate descriptionMatch = builder.like(posts.get("description"), "%" + keyword + "%");

            criteria.where(builder.or(titleMatch, descriptionMatch));

            Predicate allPostsByKeyword = builder.or(titleMatch, descriptionMatch);
            criteria.where(builder.and(tagMatch, allPostsByKeyword));

            // 然后把这个query给session运行并返回结果
            return new HashSet<>(session.createQuery(criteria).getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new HashSet<>();
    }
}
