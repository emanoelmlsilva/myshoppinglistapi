package com.example.myshoppinglistapikotlin.controller

import com.example.myshoppinglistapikotlin.entities.ItemList
import com.example.myshoppinglistapikotlin.exceptions.ObjectNotFoundException
import com.example.myshoppinglistapikotlin.services.ItemListService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/item_list")
class ItemListController {

    @Autowired
    lateinit var itemListService: ItemListService

    @GetMapping()
    fun findAll() : List<ItemList>{
        return itemListService.findAll()
    }

    @GetMapping("/credit_card/{id}")
    fun findAllByIdCard(@PathVariable("id") id: Long) : List<ItemList> {
        return itemListService.findAllByIdCreditCard(id)
    }

    @PostMapping()
    fun save(@RequestBody itemList: ItemList) : ResponseEntity<ItemList>{
        return ResponseEntity<ItemList>(itemListService.saveItemList(itemList), HttpStatus.OK)
    }

    @PutMapping()
    fun update(@RequestBody itemList: ItemList) : ResponseEntity<ItemList> {
        return try {
            ResponseEntity<ItemList>(itemListService.updateItemList(itemList), HttpStatus.OK)
        } catch (objectNotFoundException: ObjectNotFoundException){
            ResponseEntity<ItemList>(ItemList(), HttpStatus.NOT_FOUND)
        }
    }
}