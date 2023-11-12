package com.example.myshoppinglistapikotlin.services

import com.example.myshoppinglistapikotlin.entities.Purchase
import com.example.myshoppinglistapikotlin.exceptions.ObjectNotFoundException
import com.example.myshoppinglistapikotlin.exceptions.PurchaseExistException
import com.example.myshoppinglistapikotlin.repositories.PurchaseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PurchaseService {

    @Autowired
    lateinit var purchaseRepository: PurchaseRepository

    fun findAllPurchaseByIdCreditCard(idCreditCard: Long) : List<Purchase>{
        return purchaseRepository.findAllByCreditCardId(idCreditCard)
    }

    fun findById(id: Long) : Purchase {
        val purchase = purchaseRepository.findById(id)
        return if(purchase.isPresent) purchase.get() else throw ObjectNotFoundException("Produto não encontrado!")
    }

    fun findAll() : List<Purchase>{
        return purchaseRepository.findAll()
    }

    fun savePurchase(purchase: Purchase) : Purchase {
        try {
            findById(purchase.id)
            throw PurchaseExistException("Produto já existe")
        }catch (objectNotFoundException: ObjectNotFoundException){
            return purchaseRepository.save(purchase)
        }
    }

    fun updatePurchase(purchase: Purchase) : Purchase {
        try{
            findById(purchase.id)
            return purchaseRepository.save(purchase)
        }catch (objectNotFoundException: ObjectNotFoundException){
            throw objectNotFoundException
        }
    }

    fun deletePurchase(id: Long){
        try {
            findById(id)
            return purchaseRepository.deleteById(id)
        }catch (objectNotFoundException: ObjectNotFoundException){
            throw PurchaseExistException("Produto NÃO existe")
        }

    }
}