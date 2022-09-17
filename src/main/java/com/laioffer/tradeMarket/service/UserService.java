package com.laioffer.tradeMarket.service;

import com.laioffer.tradeMarket.dao.UserDao;
import com.laioffer.tradeMarket.entity.Post;
import com.laioffer.tradeMarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void signUp(User user) throws Exception {
        User existingUser = getUserByUsername(user.getUsername());
        if (existingUser != null) {
            throw new Exception("Username already exists!");
        }
        existingUser = getUserByEmail(user.getEmail());
        if (existingUser != null) {
            throw new Exception("Email already exists!");
        }


        user.setEnabled(true);
        user.setPostList(new HashSet<>());
        user.setPurchaseHistory(new HashSet<>());
        user.setSellingHistory(new HashSet<>());
        try {
            userDao.signUp(user);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

}
