package com.example.myshoppinglistapikotlin.repositories

import com.example.myshoppinglistapikotlin.entities.CreditCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditCardRepository : JpaRepository<CreditCard, Long> {

    fun findAllByUserEmail(userEmail: String): List<CreditCard>

}