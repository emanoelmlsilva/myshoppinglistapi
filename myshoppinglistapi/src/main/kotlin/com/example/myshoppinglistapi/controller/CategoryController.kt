package com.example.myshoppinglistapi.controller

import com.example.myshoppinglistapi.entities.Category
import com.example.myshoppinglistapi.entities.CreditCard
import com.example.myshoppinglistapi.exceptions.CategoryExistException
import com.example.myshoppinglistapi.exceptions.CreditCardExistException
import com.example.myshoppinglistapi.exceptions.ObjectNotFoundException
import com.example.myshoppinglistapi.services.CategoryService
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
@RequestMapping("/category")
class CategoryController() {

    @Autowired
    lateinit var categoryService: CategoryService

    @GetMapping()
    fun findAllCategory(): List<Category>{
        return categoryService.findAllCategory()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<Category>{
        return try{
            ResponseEntity<Category>(categoryService.findById(id), HttpStatus.OK)
        }catch (objectNotFoundException: ObjectNotFoundException){
            ResponseEntity<Category>(Category(), HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping()
    fun save(@RequestBody category: Category): ResponseEntity<Category> {
        return try{
            ResponseEntity<Category>(categoryService.saveCategory(category), HttpStatus.OK)
        }catch (categoryExistException: CategoryExistException){
            ResponseEntity<Category>(Category(), HttpStatus.CONFLICT)
        }
    }

    @PutMapping()
    fun update(@RequestBody category: Category): ResponseEntity<Category>{
        return try{
            ResponseEntity<Category>(categoryService.updateCategory(category), HttpStatus.OK)
        }catch (objectNotFoundException: ObjectNotFoundException){
            ResponseEntity<Category>(Category(), HttpStatus.NOT_FOUND)
        }
    }
}