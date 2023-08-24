package com.stockmanagment.app.service;

import com.stockmanagment.app.dto.*;
import com.stockmanagment.app.model.Article;
import com.stockmanagment.app.model.Category;
import com.stockmanagment.app.model.Equipement;
import com.stockmanagment.app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    public CategoryResponseDto createCategory(CategoryRequestDto requestDto) {
        Category category = convertToEntity(requestDto);
        Category savedCategory = categoryRepository.save(category);
        return convertToResponseDTO(savedCategory);
    }
    private CategoryResponseDto convertToResponseDTO(Category category) {
        CategoryResponseDto responseDTO = new CategoryResponseDto();
        responseDTO.setId(category.getId());
        responseDTO.setNom(category.getNom());

        return responseDTO;
    }
    private Category  convertToEntity(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setNom(requestDto.getNom());
        return category;
    }

    public Optional<Category> updateCategory(Long id, Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            category.setId(id);// Set the ID in case it's not set already
            return Optional.of(categoryRepository.save(category));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteCategory(Long id) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            categoryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public long getTotalCategories() {
        return categoryRepository.count();
    }
}