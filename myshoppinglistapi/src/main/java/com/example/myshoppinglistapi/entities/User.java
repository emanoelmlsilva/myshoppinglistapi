package com.example.myshoppinglistapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.antlr.v4.runtime.misc.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class User {

    @Id
    @Column(name = "email")
    private String email = "";

    @NotNull
    @Column(name = "password")
    private String password = "";

    @NotNull
    @Column(name = "name")
    private String name = "";

    @NotNull
    @Column(name = "nickName")
    private String nickName = "";

    @NotNull
    @Column(name = "idAvatar")
    private int idAvatar = 0;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<CreditCard> creditCardCollection = new ArrayList();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Category> categoryCollection = new ArrayList();

    public User() {
    }

    public User(String email, String password, String name, String nickName, int idAvatar) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.idAvatar = idAvatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getIdAvatar() {
        return idAvatar;
    }

    public void setIdAvatar(int idAvatar) {
        this.idAvatar = idAvatar;
    }

    public List<CreditCard> getCreditCardCollection() {
        return creditCardCollection;
    }

    public void setCreditCardCollection(List<CreditCard> creditCardCollection) {
        this.creditCardCollection = creditCardCollection;
    }

    public List<Category> getCategoryCollection() {
        return categoryCollection;
    }

    public void setCategoryCollection(List<Category> categoryCollection) {
        this.categoryCollection = categoryCollection;
    }
}
