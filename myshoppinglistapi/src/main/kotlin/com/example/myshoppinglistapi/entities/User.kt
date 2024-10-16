package com.example.myshoppinglistapi.entities

import com.example.myshoppinglistapi.utils.Status
import com.fasterxml.jackson.annotation.JsonIgnore
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
@Table(name = "users")
class User() {

    @field:Id
    @field:Column(name = "email")
    var email: String = ""

    @field:NotNull
    @field:Column(name = "password")
    var password: String = ""

    @field:NotNull
    @field:Column(name = "name")
    var name: String = ""

    @field:NotNull
    @field:Column(name = "nickName")
    var nickName: String = ""

    @field:NotNull
    @field:Column(name = "idAvatar")
    var idAvatar: Int = 0

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    var creditCardCollection: List<CreditCard> = listOf()

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    var categoryCollection: List<Category> = listOf()

    @field:NotNull
    @field:Column(name = "status")
    var status: Status = Status.INCOMPLETE
    constructor(
        email: String,
        password: String,
        name: String,
        nickName: String,
        idAvatar: Int,
        status: Status
    ) : this()
}