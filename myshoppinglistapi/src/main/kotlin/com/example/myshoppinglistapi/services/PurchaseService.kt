package com.example.myshoppinglistapi.services

import com.example.myshoppinglistapi.entities.Purchase
import com.example.myshoppinglistapi.exceptions.ObjectNotFoundException
import com.example.myshoppinglistapi.exceptions.PurchaseExistException
import com.example.myshoppinglistapi.repositories.PurchaseRepository
import com.example.myshoppinglistapi.utils.FormattedDate
import com.example.myshoppinglistapi.utils.TypeFrequencyRepeat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PurchaseService {

    @Autowired
    lateinit var purchaseRepository: PurchaseRepository

    fun findAllPurchaseByIdCreditCard(idCreditCard: Long): List<Purchase> {
        return purchaseRepository.findAllByCreditCardId(idCreditCard)
    }

    fun findById(id: Long): Purchase {
        val purchase = purchaseRepository.findById(id)
        return if (purchase.isPresent) purchase.get() else throw ObjectNotFoundException("Produto não encontrado!")
    }

    fun findAll(): List<Purchase> {
        return purchaseRepository.findAll()
    }

    fun savePurchase(purchase: Purchase): Purchase {
        try {
            findById(purchase.id)
            throw PurchaseExistException("Produto já existe")
        } catch (objectNotFoundException: ObjectNotFoundException) {
            return purchaseRepository.save(purchase)
        }
    }

    fun savePurchase(idOriginal: Long, purchase: Purchase): Purchase {
        try {
            findById(purchase.id)
            throw PurchaseExistException("Produto já existe")
        } catch (objectNotFoundException: ObjectNotFoundException) {
            val purchaseOriginal = findById(idOriginal)
            val newDateRepeat = FormattedDate().incrementDate(
                purchaseOriginal.date_repeat,
                purchaseOriginal.frequency_repeat.getMonth().toLong()
            )
            purchaseOriginal.date_repeat = newDateRepeat
            purchaseOriginal.repeat_purchase_date = FormattedDate().dateCurrent()

            purchaseRepository.save(purchaseOriginal)

            return purchaseRepository.save(purchase)
        }
    }

    fun updatePurchase(purchase: Purchase): Purchase {
        try {
            findById(purchase.id)
            return purchaseRepository.save(purchase)
        } catch (objectNotFoundException: ObjectNotFoundException) {
            throw objectNotFoundException
        }
    }

    fun deletePurchase(id: Long) {
        try {
            findById(id)
            return purchaseRepository.deleteById(id)
        } catch (objectNotFoundException: ObjectNotFoundException) {
            throw PurchaseExistException("Produto NÃO existe")
        }

    }

    fun getPurchaseRepeatHabilitated(dateCurrent: String): List<Purchase> {
        try {
            return purchaseRepository.findAllPurchaseRepeatDate(dateCurrent)
        } catch (objectNotFoundException: ObjectNotFoundException) {
            throw PurchaseExistException("Nenhuma Compra habilitade para compra repetida")
        }
    }

    fun removeRepeatPurchase(idPurchase: Long): Purchase {
        try {
            val purchase = findById(idPurchase)
            purchase.is_repeat = false
            purchase.repeat_purchase_date = ""
            purchase.frequency_repeat = TypeFrequencyRepeat.NEVER

            return purchaseRepository.save(purchase)
        } catch (objectNotFoundException: ObjectNotFoundException) {
            throw PurchaseExistException("Produto NÃO existe")
        }
    }
}