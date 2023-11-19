package com.example.myshoppinglistapi.controller;

import com.example.myshoppinglistapi.entities.Category;
import com.example.myshoppinglistapi.entities.User;
import com.example.myshoppinglistapi.exceptions.ObjectNotFoundException;
import com.example.myshoppinglistapi.exceptions.UserExistException;
import com.example.myshoppinglistapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
//    @Autowired
   //CategoryService categoryService;

    @GetMapping()
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @GetMapping("/{email}/{password}")
    public ResponseEntity<User> findById(@PathVariable("email") String email, @PathVariable("password") String password){
        try{
            return new ResponseEntity<User>(userService.findById(email, password), HttpStatus.OK);
        }catch (ObjectNotFoundException objectNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new User());
        }
    }

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user){
         try{
             ResponseEntity<User> userResponse = new ResponseEntity<User>(userService.saveUser(user), HttpStatus.OK);
           if(userResponse.getStatusCode() == HttpStatus.OK){
               Category category = new Category();
               category.setCategory("Mercado");
               category.setIdImage("outline_shopping_basket_black_36.png");
               category.setColor(-15728802);
//               category.setUser(user);
               //categoryService.saveCategory(category);
               return userResponse;
           }
        }catch (UserExistException userExistException){
             new ResponseEntity<Void>((MultiValueMap<String, String>) new User(), HttpStatus.CONFLICT);
        }
         return null;
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user){
        try{
            return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
        }catch (ObjectNotFoundException objectNotFoundException){
             return new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
        }
    }
}