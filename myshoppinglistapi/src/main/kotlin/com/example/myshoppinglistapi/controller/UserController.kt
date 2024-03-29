package com.example.myshoppinglistapi.controller

import com.example.myshoppinglistapi.entities.Category
import com.example.myshoppinglistapi.services.UserService
import com.example.myshoppinglistapi.entities.User
import com.example.myshoppinglistapi.exceptions.ObjectNotFoundException
import com.example.myshoppinglistapi.exceptions.UserExistException
import com.example.myshoppinglistapi.services.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.awt.Color

@RestController
@RequestMapping("/user")
class UserController() {

    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var categoryService: CategoryService

    @GetMapping()
    fun findAllUser(): List<User>{
        return userService.findAllUser()
    }

    @GetMapping("/{email}/{password}")
    fun findById(@PathVariable("email") email: String, @PathVariable("password") password: String): ResponseEntity<User> {
        return try{
            ResponseEntity<User>(userService.findById(email, password), HttpStatus.OK)
        }catch (objectNotFoundException: ObjectNotFoundException){
            ResponseEntity<User>(User(), HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping()
    fun save(@RequestBody user: User): ResponseEntity<Any> {
        return try {
            val userResponse = ResponseEntity<Any>(userService.saveUser(user), HttpStatus.OK)
            if (userResponse.statusCode == HttpStatus.OK){
                val category = Category()
                category.category = "Mercado"
                category.idImage = "outline_shopping_basket_black_36.png"
                category.color = -15728802
                category.user = user
                categoryService.saveCategory(category)
            }
            return userResponse
        }catch(exception: UserExistException){
            ResponseEntity<Any>(User(), HttpStatus.CONFLICT)
        }
    }

    @PutMapping()
    fun update(@RequestBody user: User): ResponseEntity<User>{
        return try{
            ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK)
        }catch (objectNotFoundException: ObjectNotFoundException){
            ResponseEntity<User>(User(), HttpStatus.NOT_FOUND)
        }
    }
}