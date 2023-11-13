package com.example.myshoppinglistapi.entities;

import javax.persistence.*;

public class ItemList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idItemList")
    private Long id = 0L;

    @Column(name = "item")
    private String item = "";

    @Column(name = "isRemoved")
    private Boolean isRemoved = false;

    @ManyToOne
    @JoinColumn(name = "creditCard_id")
    private CreditCard creditCard = new CreditCard();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category = new Category();
}
