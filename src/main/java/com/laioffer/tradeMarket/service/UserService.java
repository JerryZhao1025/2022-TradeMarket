package com.laioffer.tradeMarket.service;

import com.laioffer.tradeMarket.dao.UserDao;
<<<<<<< HEAD
=======
import com.laioffer.tradeMarket.entity.Post;
>>>>>>> 2038fe1 (Signup feature implemented.)
import com.laioffer.tradeMarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void signUp(User user) throws Exception {
<<<<<<< HEAD
        User existingUser = getUser(user.getUsername());
=======
        User existingUser = getUserByUsername(user.getUsername());
>>>>>>> 2038fe1 (Signup feature implemented.)
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

<<<<<<< HEAD
    public User getUser(String username) {
=======
    public User getUserByUsername(String username) {
>>>>>>> 2038fe1 (Signup feature implemented.)
        return userDao.getUserByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

}
