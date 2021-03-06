package com.sda.auction.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
    private List<Product> productList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Bid> bidList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "winner")
    private List<Product> wonProductList;
}
