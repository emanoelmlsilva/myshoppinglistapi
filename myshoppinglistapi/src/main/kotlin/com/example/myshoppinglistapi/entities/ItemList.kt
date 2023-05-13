package com.example.myshoppinglistapi.entities

import javax.persistence.*

@Entity
@Table(name = "itemLists")
class ItemList() {

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "idItemList")
    var id: Long = 0

    @field:Column(name = "item")
    var item: String = ""

    @field:Column(name = "isRemoved")
    var isRemoved: Boolean = false

    @ManyToOne
    @JoinColumn(name = "creditCard_id")
    var creditCard: CreditCard = CreditCard()

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category = Category()

    constructor(item: String, isRemoved: Boolean) : this() {
        this.item = item
        this.isRemoved = isRemoved
    }
}
