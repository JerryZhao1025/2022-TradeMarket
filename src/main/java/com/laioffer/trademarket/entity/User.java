package com.laioffer.trademarket.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 2652327633296064143L;

    @Id
    private String email;
    private String lastname;
    private String firstname;
    private String password;
    private String phoneNumber;
    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Post> postList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Post> myCart;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Post> favoriteList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Order> historyOrderList;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public List<Post> getMyCart() {
        return myCart;
    }

    public void setMyCart(List<Post> myCart) {
        this.myCart = myCart;
    }

    public List<Post> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Post> favoriteList) {
        this.favoriteList = favoriteList;
    }

    public List<Order> getHistoryOrderList() {
        return historyOrderList;
    }

    public void setHistoryOrderList(List<Order> historyOrderList) {
        this.historyOrderList = historyOrderList;
    }
}
