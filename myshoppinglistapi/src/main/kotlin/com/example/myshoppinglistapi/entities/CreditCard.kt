package com.example.myshoppinglistapi.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "credit_cards")
class CreditCard {

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "idCreditCard")
    var id: Long = 0

    @field:Column(name = "holderName")
    var holderName: String = ""

    @field:Column(name = "cardName")
    var cardName: String = ""

    @field:Column(name = "value")
    var value: Float = 0F

    @field:Column(name = "colorCard")
    var colorCard: Int = 0

    @field:Column(name = "typeCard")
    var typeCard: Int = 0

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User = User()

    @field:Column(name = "flag")
    var flag: Int = 0

    @field:Column(name = "position")
    var position: Int = 0

    @field:Column(name = "dayClosedInvoice")
    var dayClosedInvoice: Int = 0

    @OneToMany(mappedBy = "creditCard")
    @JsonIgnore
    var purchaseCollection: List<Purchase> = listOf()

    @OneToMany(mappedBy = "creditCard")
    @JsonIgnore
    var itemListCollection: List<ItemList> = listOf()

    constructor()

    constructor(
        holderName: String,
        cardName: String,
        value: Float,
        colorCard: Int,
        typeCard: Int,
        user: User,
        flag: Int,
        position: Int,
        dayClosedInvoice: Int
    ) {
        this.holderName = holderName
        this.cardName = cardName
        this.value = value
        this.colorCard = colorCard
        this.typeCard = typeCard
        this.user = user
        this.flag = flag
        this.position = position
        this.dayClosedInvoice = dayClosedInvoice
    }
}