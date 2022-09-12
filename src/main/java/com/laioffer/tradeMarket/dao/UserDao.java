package com.laioffer.tradeMarket.dao;

import com.laioffer.tradeMarket.entity.Tag;
import com.laioffer.tradeMarket.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public User searchUserByID(int userID){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.get(User.class, userID);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }
}
