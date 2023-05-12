package com.example.myshoppinglistapi.controller

import com.example.myshoppinglistapi.entities.CreditCard
import com.example.myshoppinglistapi.exceptions.CreditCardExistException
import com.example.myshoppinglistapi.exceptions.ObjectNotFoundException
import com.example.myshoppinglistapi.services.CreditCardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/credit_card")
class CreditCardController() {

    @Autowired
    lateinit var creditCardService : CreditCardService

    @GetMapping()
    fun findAllCreditCard(): List<CreditCard>{
        return creditCardService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<CreditCard>{
        return try{
            ResponseEntity<CreditCard>(creditCardService.findById(id), HttpStatus.OK)
        }catch (objectNotFoundException: ObjectNotFoundException){
            ResponseEntity<CreditCard>(CreditCard(), HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/email/{email}")
    fun findAllByEmail(@PathVariable("email") email: String): ResponseEntity<List<CreditCard>>{
        return try{
            ResponseEntity<List<CreditCard>>(creditCardService.findAllByEmail(email), HttpStatus.OK)
        }catch (objectNotFoundException: ObjectNotFoundException){
            ResponseEntity<List<CreditCard>>(listOf(), HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping()
    fun save(@RequestBody creditCard: CreditCard): ResponseEntity<CreditCard> {
        return try{
            ResponseEntity<CreditCard>(creditCardService.saveCreditCard(creditCard), HttpStatus.OK)
        }catch (creditCardExistException: CreditCardExistException){
            ResponseEntity<CreditCard>(CreditCard(), HttpStatus.CONFLICT)
        }
    }

    @PutMapping()
    fun update(@RequestBody creditCard: CreditCard): ResponseEntity<CreditCard>{
        return try{
            ResponseEntity<CreditCard>(creditCardService.updateCreditCard(creditCard), HttpStatus.OK)
        }catch (objectNotFoundException: ObjectNotFoundException){
            ResponseEntity<CreditCard>(CreditCard(), HttpStatus.NOT_FOUND)
        }
    }
}