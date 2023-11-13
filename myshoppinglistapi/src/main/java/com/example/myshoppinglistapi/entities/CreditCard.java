package com.example.myshoppinglistapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCreditCard")
    private Long id = 0L;

    @Column(name = "holderName")
    private String holderName = "";

    @Column(name = "cardName")
    private String cardName = "";

    @Column(name = "value")
    private Float value = 0F;

    @Column(name = "colorCard")
    private int colorCard = 0;

    @Column(name = "typeCard")
    private int typeCard = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user = new User();

    @Column(name = "flag")
    private int flag = 0;

    @Column(name = "position")
    private int position = 0;

    @OneToMany(mappedBy = "creditCard")
    @JsonIgnore
    private List<Purchase> purchaseCollection = new ArrayList();

    @OneToMany(mappedBy = "creditCard")
    @JsonIgnore
    private List<ItemList> itemListCollection = new ArrayList();

    public CreditCard() {
    }

    public CreditCard(Long id, String holderName, String cardName, Float value, int colorCard, int typeCard, User user, int flag, int position) {
        this.id = id;
        this.holderName = holderName;
        this.cardName = cardName;
        this.value = value;
        this.colorCard = colorCard;
        this.typeCard = typeCard;
        this.user = user;
        this.flag = flag;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public int getColorCard() {
        return colorCard;
    }

    public void setColorCard(int colorCard) {
        this.colorCard = colorCard;
    }

    public int getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(int typeCard) {
        this.typeCard = typeCard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Purchase> getPurchaseCollection() {
        return purchaseCollection;
    }

    public void setPurchaseCollection(List<Purchase> purchaseCollection) {
        this.purchaseCollection = purchaseCollection;
    }

    public List<ItemList> getItemListCollection() {
        return itemListCollection;
    }

    public void setItemListCollection(List<ItemList> itemListCollection) {
        this.itemListCollection = itemListCollection;
    }
}
