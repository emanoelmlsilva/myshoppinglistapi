package com.example.myshoppinglistapi.services

import com.example.myshoppinglistapi.entities.CreditCard
import com.example.myshoppinglistapi.exceptions.CreditCardExistException
import com.example.myshoppinglistapi.exceptions.ObjectNotFoundException
import com.example.myshoppinglistapi.repositories.CreditCardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreditCardService() {

    @Autowired
    lateinit var creditCardRepository: CreditCardRepository

    fun findById(id: Long): CreditCard{
        val creditCard = creditCardRepository.findById(id)
        return if(creditCard.isPresent) creditCard.get() else throw ObjectNotFoundException("Cartão não encontrado! cartão: ${id}")
    }

    fun findAll(): List<CreditCard>{
        return creditCardRepository.findAll()
    }

    fun findAllByEmail(email: String): List<CreditCard>{
        return creditCardRepository.findAllByUserEmail(email)
    }

    fun saveCreditCard(creditCard: CreditCard): CreditCard{
        try{
            findById(creditCard.id)
            throw CreditCardExistException("Cartão já existe!")
        }catch(objectNotFoundException: ObjectNotFoundException){
            return creditCardRepository.save(creditCard)
        }
    }

    fun updateCreditCard(creditCard: CreditCard): CreditCard{
        try{
            findById(creditCard.id)
            return creditCardRepository.save(creditCard)
        }catch (objectNotFoundException: ObjectNotFoundException){
            throw objectNotFoundException
        }
    }
}