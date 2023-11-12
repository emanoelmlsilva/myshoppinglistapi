package com.example.myshoppinglistapikotlin.repositories

import com.example.myshoppinglistapikotlin.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, String> {

    fun findByEmailAndPassword(email: String, password: String) : Optional<User>
}