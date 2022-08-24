package com.laioffer.trademarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order")
public class Order implements Serializable {

    private static final long serialVersionUID = 4293887464938867671L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Post post;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createTimeStamp;

    private boolean paid;

    private boolean shipped;


}
