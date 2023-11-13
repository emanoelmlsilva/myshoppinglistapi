package com.example.myshoppinglistapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id = 0L;

    @Column(name = "category")
    private String category = "";

    @Column(name = "idImage")
    private String idImage = "";

    @Column(name = "color")
    private int color = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user = new User();

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Purchase> purchaseCollection = new ArrayList();

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<ItemList> itemListCollection = new ArrayList();

    public Category() {
    }

    public Category(Long id, String category, String idImage, int color) {
        this.id = id;
        this.category = category;
        this.idImage = idImage;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
