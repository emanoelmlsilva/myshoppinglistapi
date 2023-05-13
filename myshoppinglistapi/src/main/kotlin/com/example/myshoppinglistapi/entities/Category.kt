package com.example.myshoppinglistapi.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "categories")
class Category() {

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "idCategory")
    var id: Long = 0L

    @field:Column(name = "category")
    var category: String = ""

    @field:Column(name = "idImage")
    var idImage: Int = 0

    @field:Column(name = "color")
    var color: Int = 0

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User = User()

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    var categoryCollection: List<Category> = listOf()
    constructor(id: Long, category: String, idImage: Int, color: Int) : this(){
        this.id = id
        this.category = category
        this.idImage = idImage
        this.color = color
    }

}