package com.laioffer.trademarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 4293887464938867671L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JsonIgnore
    private User buyer;

    @ManyToOne
    @JsonIgnore
    private User seller;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Post post;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestampAtCreation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Timestamp getTimestampAtCreation() {
        return timestampAtCreation;
    }

    public void setTimestampAtCreation(Timestamp timestampAtCreation) {
        this.timestampAtCreation = timestampAtCreation;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
