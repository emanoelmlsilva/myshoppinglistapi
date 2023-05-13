package com.example.myshoppinglistapi.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "purchases")
class Purchase() {

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "idPurchase")
    var id: Long = 0L

    @field:Column(name = "name")
    var name: String = ""

    @field:Column(name = "locale")
    var locale: String = ""

    @field:Column(name = "quantiOrKilo")
    var quantiOrKilo: String = ""

    @field:Column(name = "typeProduct")
    var typeProduct: Int = 0

    @field:Column(name = "date")
    var date: String = "24-01-2022"

    @field:Column(name = "price")
    var price: Double = 0.0

    @field:Column(name = "discount")
    var discount: Double = 0.0

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category = Category()

    @ManyToOne
    @JoinColumn(name = "credit_card_id")
    var creditCard: CreditCard = CreditCard()

    constructor(
        id: Long,
        name: String,
        locale: String,
        quantiOrKilo: String,
        typeProduct: Int,
        date: String,
        price: Double,
        discount: Double
    ) : this() {
        this.id = id
        this.name = name
        this.locale = locale
        this.quantiOrKilo = quantiOrKilo
        this.typeProduct = typeProduct
        this.date = date
        this.price = price
        this.discount = discount
    }


}