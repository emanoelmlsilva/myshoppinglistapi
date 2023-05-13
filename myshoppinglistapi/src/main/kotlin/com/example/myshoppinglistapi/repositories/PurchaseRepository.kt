package com.example.myshoppinglistapi.repositories

import com.example.myshoppinglistapi.entities.Purchase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseRepository : JpaRepository<Purchase, Long> {

    fun findAllByCreditCardId(id: Long) : List<Purchase>
}