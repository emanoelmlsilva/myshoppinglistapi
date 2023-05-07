package com.example.myshoppinglistapi.services

import com.example.myshoppinglistapi.entities.User
import com.example.myshoppinglistapi.exceptions.ObjectNotFoundException
import com.example.myshoppinglistapi.exceptions.UserExistException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.example.myshoppinglistapi.repositories.UserRepository
import java.lang.Exception
import java.util.*

@Service
class UserService() {

    @Autowired
    lateinit var userRepository: UserRepository

    fun findAllUser(): List<User>{
        return userRepository.findAll()
    }

    fun findById(email: String) : User {
        val user = userRepository.findById(email)
        return if(user.isPresent) user.get() else throw ObjectNotFoundException("Usuário não encontrado! email: ${email}")
    }

    fun saveUser(user: User){
        try{
            findById(user.email)
            throw UserExistException("Usuário já existe!")
        }catch (objectNotFoundException: ObjectNotFoundException){
            userRepository.save(user)
        }
    }
}