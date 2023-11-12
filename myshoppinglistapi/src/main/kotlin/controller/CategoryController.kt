package com.example.myshoppinglistapikotlin.controller

import com.example.myshoppinglistapikotlin.entities.Category
import com.example.myshoppinglistapikotlin.exceptions.CategoryExistException
import com.example.myshoppinglistapikotlin.exceptions.ObjectNotFoundException
import com.example.myshoppinglistapikotlin.services.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/email/{email}")
    fun findByEmail(@PathVariable("email") email: String): ResponseEntity<List<Category>>{
        return try{
            ResponseEntity<List<Category>>(categoryService.findAllByEmail(email), HttpStatus.OK)
        }catch (objectNotFoundException: ObjectNotFoundException){
            ResponseEntity<List<Category>>(listOf(), HttpStatus.NOT_FOUND)
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