package com.laioffer.tradeMarket.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 2652327633296064143L;

    @Id
    private String username;
    private String password;

    private String firstname;
    private String lastname;

    @Column(unique = true)
    private String email;

    private String phoneNumber;
    private boolean enabled;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<Post> postList;
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
//    private List<Post> myCart;
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
//    private List<Post> favoriteList;
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Order> purchaseHistory;
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<Order> sellingHistory;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Post> getPostList() {
        return postList;
    }

    public void setPostList(Set<Post> postList) {
        this.postList = postList;
    }

//    public List<Post> getMyCart() {
//        return myCart;
//    }
//
//    public void setMyCart(List<Post> myCart) {
//        this.myCart = myCart;
//    }
//
//    public List<Post> getFavoriteList() {
//        return favoriteList;
//    }
//
//    public void setFavoriteList(List<Post> favoriteList) {
//        this.favoriteList = favoriteList;
//    }

    public Set<Order> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(Set<Order> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public Set<Order> getSellingHistory() {
        return sellingHistory;
    }

    public void setSellingHistory(Set<Order> sellingHistory) {
        this.sellingHistory = sellingHistory;
    }
}
