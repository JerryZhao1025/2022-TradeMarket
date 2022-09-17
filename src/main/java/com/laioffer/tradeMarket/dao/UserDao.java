package com.laioffer.tradeMarket.dao;

import com.laioffer.tradeMarket.entity.Authorities;
import com.laioffer.tradeMarket.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void signUp(User user) throws Exception {
        Authorities authorities = new Authorities();
        authorities.setUsername(user.getUsername());
        authorities.setAuthorities("ROLE_USER");
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(user);
            session.getTransaction().commit();

        } catch (Exception exception) {
            exception.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
<<<<<<< HEAD
=======


>>>>>>> 2038fe1 (Signup feature implemented.)
    }

    public User getUserByUsername(String username) {
        User user = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            user = session.get(User.class, username);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
<<<<<<< HEAD
=======
        } finally {
            if (session != null) {
                session.close();
            }
>>>>>>> 2038fe1 (Signup feature implemented.)
        }
        return user;
    }

    public User getUserByEmail(String email) {
        User user = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            Predicate predicate = builder.and(builder.equal(userRoot.get("email"), email));
            criteriaQuery.select(userRoot).where(predicate);
            TypedQuery<User> query = session.createQuery(criteriaQuery);
            user = query.getSingleResult();
        } catch (Exception exception) {
            exception.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }
<<<<<<< HEAD
=======



>>>>>>> 2038fe1 (Signup feature implemented.)
}
