package com.example.blog.app.service;

import com.example.blog.app.domain.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listCategories();
    Category createCategory(Category category);
}
