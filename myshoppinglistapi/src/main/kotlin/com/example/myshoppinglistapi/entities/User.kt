package com.example.myshoppinglistapi.entities

import org.jetbrains.annotations.NotNull
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

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

    constructor(
        email: String,
        password: String,
        name: String,
        nickName: String,
        idAvatar: Int,
    ) : this()
}