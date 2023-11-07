package com.example.myshoppinglistapi.services

import com.example.myshoppinglistapi.entities.Category
import com.example.myshoppinglistapi.exceptions.CategoryExistException
import com.example.myshoppinglistapi.exceptions.ObjectNotFoundException
import com.example.myshoppinglistapi.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryService {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    fun findAllByEmail(email: String): List<Category>{
        return categoryRepository.findAllByUserEmail(email)
    }
    fun findById(id: Long): Category{
        val category = categoryRepository.findById(id)
        return if(category.isPresent) category.get() else throw ObjectNotFoundException("Categoria não encontrada!")
    }

    fun findAllCategory() : List<Category>{
        return categoryRepository.findAll()
    }

    fun saveCategory(category: Category) : Category{
        try{
            findById(category.id)
            throw CategoryExistException("Categoria já existe!")
        }catch (objectNotFoundException: ObjectNotFoundException){
            return categoryRepository.save(category)
        }
    }

    fun updateCategory(category: Category) : Category{
        try {
            findById(category.id)
            return categoryRepository.save(category)
        }catch (objectNotFoundException: ObjectNotFoundException){
            throw objectNotFoundException
        }
    }
}