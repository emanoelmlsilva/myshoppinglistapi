package com.example.myshoppinglistapi.entities;

import javax.persistence.*;

public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPurchase")
    private Long id = 0L;

    @Column(name = "name")
    private String name = "";

    @Column(name = "locale")
    private String locale = "";

    @Column(name = "quantiOrKilo")
    private String quantiOrKilo = "";

    @Column(name = "typeProduct")
    private int typeProduct = 0;

    @Column(name = "date")
    private String date = "24-01-2022";

    @Column(name = "price")
    private Double price = 0.0;

    @Column(name = "discount")
    private Double discount = 0.0;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category = new Category();

    @ManyToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard = new CreditCard();

    public Purchase(Long id, String name, String locale, String quantiOrKilo, int typeProduct, String date, Double price, Double discount) {
        this.id = id;
        this.name = name;
        this.locale = locale;
        this.quantiOrKilo = quantiOrKilo;
        this.typeProduct = typeProduct;
        this.date = date;
        this.price = price;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getQuantiOrKilo() {
        return quantiOrKilo;
    }

    public void setQuantiOrKilo(String quantiOrKilo) {
        this.quantiOrKilo = quantiOrKilo;
    }

    public int getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(int typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
