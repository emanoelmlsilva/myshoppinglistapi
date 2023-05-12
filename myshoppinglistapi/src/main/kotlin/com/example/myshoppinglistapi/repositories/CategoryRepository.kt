package com.example.myshoppinglistapi.repositories

import com.example.myshoppinglistapi.entities.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
}