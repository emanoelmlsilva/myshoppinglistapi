package com.example.myshoppinglistapi.controller;

import com.example.myshoppinglistapi.entities.Category;
import com.example.myshoppinglistapi.entities.User;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @GetMapping("/{email}/{password}")
    public ResponseEntity<User> findById(@PathVariable("email") String email, @PathVariable("password") String password){
        return try{
            ResponseEntity<User>(userService.findById(email, password), HttpStatus.OK);
        }catch (ObjectNotFoundException objectNotFoundException){
            ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user){
        return try{
           ResponseEntity<User> userResponse = new ResponseEntity<User>(userService.saveUser(user), HttpStatus.OK);
           if(userResponse.statusCode() == HttpStatus.OK){
               Category category = new Category();
               category.category = "Mercado";
               category.idImage = "outline_shopping_basket_black_36.png";
               category.color = -15728802;
               category.user = user;
               categoryService.saveCategory(category);
           }
           return userResponse;
        }catch (UserExistException userExistException){
            ResponseEntity<Void>(new User(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user){
        return try{
            ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
        }catch (ObjectNotFoundException objectNotFoundException){
            ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
        }
    }
}
