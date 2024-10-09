package com.example.myshoppinglistapi.repositories

import com.example.myshoppinglistapi.entities.Purchase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface PurchaseRepository : JpaRepository<Purchase, Long> {

    fun findAllByCreditCardId(id: Long) : List<Purchase>

    @Query(value = "SELECT * FROM purchases WHERE purchases.is_repeat = TRUE AND date_repeat = :dateCurrent AND repeat_purchase_date IS NOT NULL AND repeat_purchase_date != date_repeat", nativeQuery = true)
    fun findAllPurchaseRepeatDate(@Param("dateCurrent") dateCurrent: String): List<Purchase>
}