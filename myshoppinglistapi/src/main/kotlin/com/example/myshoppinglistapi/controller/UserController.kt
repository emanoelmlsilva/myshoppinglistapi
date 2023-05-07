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

    @GetMapping("/{email}")
    fun findById(@PathVariable("email") email: String): ResponseEntity<User> {
        return try{
            ResponseEntity<User>(userService.findById(email), HttpStatus.OK)
        }catch (objectNotFoundException: ObjectNotFoundException){
            ResponseEntity<User>(User(), HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping()
    fun save(@RequestBody user: User): ResponseEntity<Any> {
        try {
            return ResponseEntity<Any>(userService.saveUser(user), HttpStatus.OK)
        }catch(exception: UserExistException){
            return ResponseEntity<Any>(User(), HttpStatus.CONFLICT)
        }
    }
}