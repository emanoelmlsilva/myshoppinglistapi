package com.example.myshoppinglistapikotlin.repositories

import com.example.myshoppinglistapikotlin.entities.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
    fun findAllByUserEmail(userEmail: String): List<Category>
}