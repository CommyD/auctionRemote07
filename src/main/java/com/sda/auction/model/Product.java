package com.sda.auction.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private ProductCategory category;
    private String description;
    private Integer startingPrice;
    private Integer minimumBidStep;
    private LocalDateTime startBiddingTime;
    private LocalDateTime endBiddingTime;

    @Lob
    private byte[] image;

    @ManyToOne(cascade = CascadeType.ALL)
    private User seller;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Bid> bidList;
}
