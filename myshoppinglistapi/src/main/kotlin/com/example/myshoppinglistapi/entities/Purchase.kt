package com.example.myshoppinglistapi.entities

import com.example.myshoppinglistapi.utils.TypeFrequencyRepeat
import javax.persistence.*

@Entity
@Table(name = "purchases")
class Purchase() {

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "id_purchase")
    var id: Long = 0L

    @field:Column(name = "name")
    var name: String = ""

    @field:Column(name = "locale")
    var locale: String = ""

    @field:Column(name = "amount_or_kilo")
    var amount_or_kilo: String = ""

    @field:Column(name = "type_product")
    var type_product: Int = 0

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

    @field:Column(name = "is_repeat")
    var is_repeat: Boolean = false

    @field:Column(name = "date_repeat")
    var date_repeat: String = ""

    @field:Column(name = "frequency_repeat")
    var frequency_repeat: TypeFrequencyRepeat = TypeFrequencyRepeat.NEVER

    @field:Column(name = "repeat_purchase_date")
    var repeat_purchase_date: String = ""

    constructor(
        id: Long,
        name: String,
        locale: String,
        quantiOrKilo: String,
        typeProduct: Int,
        date: String,
        price: Double,
        discount: Double,
        isRepeat: Boolean,
        dateRepeat: String,
        frequencyRepeat: TypeFrequencyRepeat,
        repeatPurchaseDate: String
    ) : this() {
        this.id = id
        this.name = name
        this.locale = locale
        this.amount_or_kilo = quantiOrKilo
        this.type_product = typeProduct
        this.date = date
        this.price = price
        this.discount = discount
        this.is_repeat = isRepeat
        this.date_repeat = dateRepeat
        this.frequency_repeat = frequencyRepeat
        this.repeat_purchase_date = repeatPurchaseDate
    }


}