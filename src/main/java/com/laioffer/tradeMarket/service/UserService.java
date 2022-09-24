package com.laioffer.tradeMarket.service;

import com.laioffer.tradeMarket.dao.UserDao;
import com.laioffer.tradeMarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void signUp(User user) throws Exception {
        User existingUser = getUser(user.getUsername());
        if (existingUser != null) {
            throw new Exception("Username already exists!");
        }

        if (userDao.getUserByEmail(user.getEmail()) != null) { // exist current email
            throw new Exception("Email already exists!");
        }

        user.setEnabled(true);
        user.setPostList(new ArrayList<>());
        user.setPurchaseHistory(new ArrayList<>());
        user.setSellingHistory(new ArrayList<>());
        try {
            userDao.signUp(user);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public User getUser(String username) {
        return userDao.getUserByUsername(username); // get all user info
    }

    public User getPartUserInfo(String username) {
        User user = userDao.getUserByUsername(username); // get all user info
        // remove part information
        user.setPassword(null);
        user.setSellingHistory(null);
        user.setPurchaseHistory(null);
        return user;
    }
}
