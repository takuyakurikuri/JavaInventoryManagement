package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Category;
import com.example.repository.CategoryRepository;

@Service
public class CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

}
