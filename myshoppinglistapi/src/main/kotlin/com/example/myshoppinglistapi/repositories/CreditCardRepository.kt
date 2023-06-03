package com.example.myshoppinglistapi.repositories

import com.example.myshoppinglistapi.entities.CreditCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CreditCardRepository : JpaRepository<CreditCard, Long> {

    fun findAllByUserEmail(userEmail: String): List<CreditCard>

}