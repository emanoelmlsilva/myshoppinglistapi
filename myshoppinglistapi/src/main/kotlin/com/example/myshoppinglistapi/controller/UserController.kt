package com.example.myshoppinglistapi.controller

import com.example.myshoppinglistapi.services.UserService
import com.example.myshoppinglistapi.entities.User
import com.example.myshoppinglistapi.exceptions.ObjectNotFoundException
import com.example.myshoppinglistapi.exceptions.UserExistException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController() {

    @Autowired
    lateinit var userService: UserService

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
            ResponseEntity<Any>(userService.saveUser(user), HttpStatus.OK)
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