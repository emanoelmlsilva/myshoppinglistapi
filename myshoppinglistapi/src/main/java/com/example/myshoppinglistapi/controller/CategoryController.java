package com.example.myshoppinglistapi.controller;

import com.example.myshoppinglistapi.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public List<Category> findAllCategory(){
        return categoryService.findAllCategory();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id")Long id){
        return try{
            ResponseEntity<Category>(categoryService.findById(id), HttpStatus.OK);
        }catch (ObjectNotFoundException objectNotFoundException){
            ResponseEntity<Category>(new Category(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<Category>> findByEmail(@PathVariable("email") String email){
        return try{
            ResponseEntity<List<Category>>(categoryService.findAllByEmail(email), HttpStatus.OK);
        }catch (ObjectNotFoundException objectNotFoundException){
            ResponseEntity<List<Category>>(new ArrayList(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return try{
            ResponseEntity<Category>(categoryService.saveCategory(category), HttpStatus.OK);
        }catch (CategoryExistException categoryExistException){
            ResponseEntity<Category>(new Category(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping()
    public ResponseEntity<Category> update(@RequestBody Category category){
        return try{
            ResponseEntity<Category>(categoryService.updateCategory(category), HttpStatus.OK);
        }catch (ObjectNotFoundException objectNotFoundException){
            ResponseEntity<Category>(new Category(), HttpStatus.NOT_FOUND);
        }
    }
}
