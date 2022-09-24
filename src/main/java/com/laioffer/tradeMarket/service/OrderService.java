package com.laioffer.tradeMarket.service;

import com.laioffer.tradeMarket.dao.OrderDao;
import com.laioffer.tradeMarket.dao.PostDao;
import com.laioffer.tradeMarket.dao.TagDao;
import com.laioffer.tradeMarket.dao.UserDao;
import com.laioffer.tradeMarket.entity.Order;
import com.laioffer.tradeMarket.entity.Post;
import com.laioffer.tradeMarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class OrderService {
    private final PostDao postDao;
    private final UserDao userDao;
    private final TagDao tagDao;
    private final OrderDao orderDao;

    @Autowired
    public OrderService(PostDao postDao, UserDao userDao, TagDao tagDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.tagDao = tagDao;
        this.orderDao = orderDao;
    }

    public Order newOrder(User buyer, Post post) {
        User seller = post.getOwner();
        Order order = new Order();
        order.setBuyer(buyer);
        order.setSeller(seller);
        order.setPost(post);
        order.setCreatedTime(Timestamp.from(Instant.now()));
        return orderDao.newOrder(order);
    }
}
