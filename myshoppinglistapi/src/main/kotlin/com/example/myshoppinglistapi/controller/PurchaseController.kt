package com.example.myshoppinglistapi.controller

import com.example.myshoppinglistapi.entities.Purchase
import com.example.myshoppinglistapi.entities.User
import com.example.myshoppinglistapi.exceptions.ObjectNotFoundException
import com.example.myshoppinglistapi.exceptions.PurchaseExistException
import com.example.myshoppinglistapi.services.PurchaseService
import com.example.myshoppinglistapi.utils.FormattedDate
import com.example.myshoppinglistapi.utils.TypeFrequencyRepeat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/purchase")
class PurchaseController {

    @Autowired
    lateinit var purchaseService: PurchaseService

    @GetMapping
    fun findAllPurchase(): List<Purchase> {
        return purchaseService.findAll()
    }

    @GetMapping("/credit_card/{id}")
    fun findAllPurchaseByIdCreditCard(@PathVariable("id") id: Long): List<Purchase> {
        return purchaseService.findAllPurchaseByIdCreditCard(id)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<Purchase> {
        return try {
            ResponseEntity<Purchase>(purchaseService.findById(id), HttpStatus.OK)
        } catch (objectNotFoundException: ObjectNotFoundException) {
            ResponseEntity<Purchase>(Purchase(), HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping()
    fun save(@RequestBody purchase: Purchase): ResponseEntity<Purchase> {
        return try {
            ResponseEntity<Purchase>(purchaseService.savePurchase(purchase), HttpStatus.OK)
        } catch (purchaseExistException: PurchaseExistException) {
            ResponseEntity<Purchase>(Purchase(), HttpStatus.CONFLICT)
        }
    }

    @PostMapping("/{id_original}")
    fun save(@PathVariable("id_original") idOriginal: Long, @RequestBody purchase: Purchase): ResponseEntity<Purchase> {
        return try {
            ResponseEntity<Purchase>(purchaseService.savePurchase(idOriginal, purchase), HttpStatus.OK)
        } catch (purchaseExistException: PurchaseExistException) {
            ResponseEntity<Purchase>(Purchase(), HttpStatus.CONFLICT)
        }
    }

    @PutMapping
    fun update(@RequestBody purchase: Purchase): ResponseEntity<Purchase> {
        return try {
            ResponseEntity<Purchase>(purchaseService.updatePurchase(purchase), HttpStatus.OK)
        } catch (objectNotFoundException: ObjectNotFoundException) {
            ResponseEntity<Purchase>(Purchase(), HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Void> {
        purchaseService.deletePurchase(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/repeat_date")
    fun getPurchaseRepeatHabilitated(): ResponseEntity<List<Purchase>> {
        return try {
            val testeString = FormattedDate().incrementDate(FormattedDate().dateCurrent(),
                TypeFrequencyRepeat.TWO_MONTH.getMonth().toLong()
            )
            val dateCurrent = FormattedDate().dateCurrent()
            ResponseEntity<List<Purchase>>(purchaseService.getPurchaseRepeatHabilitated(testeString), HttpStatus.OK)
        } catch (objectNotFoundException: ObjectNotFoundException) {
            ResponseEntity<List<Purchase>>(listOf(), HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/repeat_purchase/{idPurchase}")
    fun deleteRepeatPurchase(@PathVariable("idPurchase")  idPurchase: Long): ResponseEntity<Purchase> {
        return try {
            ResponseEntity<Purchase>(purchaseService.removeRepeatPurchase(idPurchase), HttpStatus.OK)
        } catch (objectNotFoundException: ObjectNotFoundException) {
            ResponseEntity<Purchase>(Purchase(), HttpStatus.NOT_FOUND)
        }
    }
}