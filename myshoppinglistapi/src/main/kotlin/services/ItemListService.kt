package com.example.myshoppinglistapikotlin.services

import com.example.myshoppinglistapikotlin.entities.ItemList
import com.example.myshoppinglistapikotlin.exceptions.ObjectNotFoundException
import com.example.myshoppinglistapikotlin.repositories.ItemListRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ItemListService {

    @Autowired
    lateinit var itemListRepository: ItemListRepository

    fun findAll() : List<ItemList>{
        return itemListRepository.findAll()
    }

    fun findAllByIdCreditCard(id: Long): List<ItemList>{
        return itemListRepository.findAllByCreditCardId(id)
    }

    fun saveItemList(itemList: ItemList) : ItemList {
        return itemListRepository.save(itemList)
    }

    fun updateItemList(itemList: ItemList) : ItemList {
        try {
            itemListRepository.findById(itemList.id)
            return itemListRepository.save(itemList)
        }catch (objectNotFoundException: ObjectNotFoundException){
            throw objectNotFoundException
        }
    }
}