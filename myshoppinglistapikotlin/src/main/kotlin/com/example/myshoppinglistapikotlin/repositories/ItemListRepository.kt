package com.example.myshoppinglistapikotlin.repositories

import com.example.myshoppinglistapikotlin.entities.ItemList
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemListRepository : JpaRepository<ItemList, Long> {

    fun findAllByCreditCardId(id: Long): List<ItemList>
}