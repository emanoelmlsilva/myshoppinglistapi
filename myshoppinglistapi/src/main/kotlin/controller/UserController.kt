package controller

import Services.UserService
import entities.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController() {

    @Autowired
    lateinit var userService: UserService

    @GetMapping()
    fun findAllUser(): List<User>{
        return userService.findAllUser()
    }

    @PostMapping()
    fun save(@RequestBody user: User){
        userService.saveUser(user)
    }
}